package com.pureandcold.aggregator.services.handlers;

import org.springframework.stereotype.Service;

import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponse;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryHandler {

    public FetchProductResponse getProductsByType(FetchProductRequest request) {

        return FetchProductResponse.builder().build();
    }
}
