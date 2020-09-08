package com.jixh.common.config;

import feign.auth.BasicAuthRequestInterceptor;
import org.springframework.context.annotation.Bean;


public class FeignAuthConfiguration {
    @Bean
    public BasicAuthRequestInterceptor basicAuthRequestInterceptor() {
        return new BasicAuthRequestInterceptor("root", "root");
    }
}
