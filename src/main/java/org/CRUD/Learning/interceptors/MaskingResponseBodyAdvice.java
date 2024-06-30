package org.CRUD.Learning.interceptors;

import org.CRUD.Learning.annotation.MaskedField;
import org.CRUD.Learning.utils.MaskingUtil;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

import jakarta.servlet.http.HttpServletRequest;
import java.lang.reflect.Field;
import java.util.Collection;

@Component
@ControllerAdvice
public class MaskingResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        // This advice will apply to all responses
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, org.springframework.http.MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType, org.springframework.http.server.ServerHttpRequest request,
                                  org.springframework.http.server.ServerHttpResponse response) {

        HttpServletRequest httpRequest = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Boolean shouldMask = (Boolean) httpRequest.getAttribute("shouldMask");
        if (shouldMask != null && shouldMask) {
            if (body instanceof Collection) {
                for (Object item : (Collection<?>) body) {
                    maskFields(item);
                }
            } else {
                maskFields(body);
            }
        }
        return body;
    }

    private void maskFields(Object object) {
        if (object == null) return;

        for (Field field : object.getClass().getDeclaredFields()) {
            if (field.isAnnotationPresent(MaskedField.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(object);
                    if (value != null) {
                        MaskedField maskedField = field.getAnnotation(MaskedField.class);
                        String maskedValue = MaskingUtil.mask(value, maskedField.strategy());
                        field.set(object, maskedValue);
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
