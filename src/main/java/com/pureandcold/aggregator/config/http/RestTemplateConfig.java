package com.pureandcold.aggregator.config.http;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {
 
    @Bean("inventoryRestTemplate")
    public RestTemplate getInventoryRestTemplate() {
        return new RestTemplate();
    }
}
