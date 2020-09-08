package com.jixh.web.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;

public class FeignAuthConfigurationweb {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptorweb() {
        return new BasicAuthRequestInterceptor("root", "root");
    }
}