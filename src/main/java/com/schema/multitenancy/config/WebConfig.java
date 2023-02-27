package com.schema.multitenancy.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.schema.multitenancy.util.Constant;



@Configuration
public class WebConfig implements WebMvcConfigurer {

	@Autowired
    private TenantRequestInterceptor tenantInterceptor;
	
	@Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(tenantInterceptor).addPathPatterns(Constant.INTERCEPTOR_PATH);
    }
	
}
