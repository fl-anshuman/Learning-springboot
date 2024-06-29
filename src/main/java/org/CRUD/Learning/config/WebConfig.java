package org.CRUD.Learning.config;

import org.CRUD.Learning.interceptors.MaskingInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    private final MaskingInterceptor maskingInterceptor;

    @Autowired
    public WebConfig(MaskingInterceptor maskingInterceptor) {
        this.maskingInterceptor = maskingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(maskingInterceptor).addPathPatterns("/**");
    }
}
