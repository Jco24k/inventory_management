package com.purchase.management.config;

import com.purchase.management.exception.CustomErrorFeignClient;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ErrorDecoder errorDecoder() {
        return new CustomErrorFeignClient();
    }
}
