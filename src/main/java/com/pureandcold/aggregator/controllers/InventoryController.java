package com.pureandcold.aggregator.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponse;
import com.pureandcold.aggregator.services.handlers.InventoryHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@RestController
@RequestMapping("1.0/api")
public class InventoryController {
    
    private final InventoryHandler inventoryHandler;

    public InventoryController(InventoryHandler inventoryHandler) {
        this.inventoryHandler = inventoryHandler;
    }

    @GetMapping("/products/fetch")
    public ResponseEntity<FetchProductResponse> getProducts(@RequestParam(name = "type", required = true) String type) {
        FetchProductRequest request = FetchProductRequest.builder().type(type).build();
        FetchProductResponse response = null;
        try {
            response = inventoryHandler.getProductsByType(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>(response, HttpStatus.ACCEPTED);
    }
    
}
