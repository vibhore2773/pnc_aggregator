package com.pureandcold.aggregator.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.services.handlers.InventoryHandler;

import static com.pureandcold.aggregator.constants.HttpConstants.InventoryController.BASE_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.InventoryController.GET_PRODUCTS_PATH;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.pureandcold.aggregator.annotation.RateLimit;


@RestController
@RequestMapping(BASE_PATH)
public class InventoryController {
    
    private final InventoryHandler inventoryHandler;

    public InventoryController(InventoryHandler inventoryHandler) {
        this.inventoryHandler = inventoryHandler;
    }

    @GetMapping(GET_PRODUCTS_PATH)
    @RateLimit(authenticatedOnly = true, limit = 50, duration = 60)
    public ResponseEntity<FetchProductResponseView> getProducts(@RequestParam(name = "type", required = true) String type) {
        FetchProductRequest request = FetchProductRequest.builder().type(type).build();
        FetchProductResponseView response = null;
        try {
            response = inventoryHandler.getProductsByType(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
}
