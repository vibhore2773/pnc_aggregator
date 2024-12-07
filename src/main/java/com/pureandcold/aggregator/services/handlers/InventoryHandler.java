package com.pureandcold.aggregator.services.handlers;

import org.springframework.stereotype.Service;

import com.pureandcold.aggregator.model.external.responses.FetchProductResponse;
import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.services.adapters.InventoryAdapter;
import com.pureandcold.aggregator.services.external.InventoryService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class InventoryHandler {

    private final InventoryService inventoryService;

    public InventoryHandler(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public FetchProductResponseView getProductsByType(FetchProductRequest request) {
        FetchProductResponse inventoryFetchProductResponse = inventoryService.getFetchProductResponse(InventoryAdapter.getInventoryRequest(request));
        return InventoryAdapter.getFetchProductResponseViewFromInventoryFetchProductResponse(inventoryFetchProductResponse);
    }
}
