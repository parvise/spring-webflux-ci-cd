package com.practice.micro.all.config;

import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Collections;

@Configuration
public class FeignWebFluxConfig {

    @Bean
    public HttpMessageConverters messageConverters() {
        return new HttpMessageConverters(Collections.emptyList());
    }
}

