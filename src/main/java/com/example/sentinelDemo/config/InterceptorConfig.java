package com.example.sentinelDemo.config;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.SentinelWebInterceptor;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.DefaultBlockExceptionHandler;
import com.alibaba.csp.sentinel.adapter.spring.webmvc.config.SentinelWebMvcConfig;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        addSpringMvcInterceptors(registry);
    }

    public void addSpringMvcInterceptors(InterceptorRegistry registry) {
        SentinelWebMvcConfig config = new SentinelWebMvcConfig();

        config.setBlockExceptionHandler(new DefaultBlockExceptionHandler());

        config.setHttpMethodSpecify(true);

        config.setWebContextUnify(true);

        config.setOriginParser(httpServletRequest -> httpServletRequest.getHeader("S-user"));

        registry.addInterceptor(new SentinelWebInterceptor(config)).addPathPatterns("/**");

    }

}
