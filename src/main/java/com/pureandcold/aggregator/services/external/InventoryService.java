package com.pureandcold.aggregator.services.external;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class InventoryService {
    private final RestTemplate restTemplate;

    public InventoryService(@Qualifier("inventoryRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getFetchProductResponse(String request) {
        String uri = "uri";
        try {
            //restTemplate.exchange(uri, request, HttpMethod.GET, String.class);
        } catch (Exception e) {
            return null;
        }
        return null;
    }
}
