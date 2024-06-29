package org.CRUD.Learning.interceptors;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import java.util.Arrays;
import java.util.List;

@Component
public class MaskingInterceptor implements HandlerInterceptor {

    private static final List<String> URLS_TO_SKIP_MASKING = Arrays.asList(
            "/employees",
            "/employees/3",
            "/employees/5"

            // Add more URLs as needed
    );

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        System.out.println("Request URI: " + requestURI);

        // Set the attribute to skip masking if the request URI matches any of the specified URLs
        if (URLS_TO_SKIP_MASKING.contains(requestURI)) {
            request.setAttribute("shouldMask", false);
        } else {
            request.setAttribute("shouldMask", true);
        }

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        // This method can remain as is, processing after the handler method execution
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // Optionally clear the attribute after processing
        request.removeAttribute("shouldMask");
    }
}
