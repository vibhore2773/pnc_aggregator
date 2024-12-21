package com.pureandcold.aggregator.services.handlers;

import com.pureandcold.aggregator.constants.FetchProductRequestTypeEnum;
import org.springframework.stereotype.Service;

import com.pureandcold.aggregator.model.external.responses.FetchProductResponse;
import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.services.adapters.InventoryAdapter;
import com.pureandcold.aggregator.services.external.InventoryService;

import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;
import java.util.List;

@Slf4j
@Service
public class InventoryHandler {

    private final InventoryService inventoryService;

    public InventoryHandler(InventoryService inventoryService) {
        this.inventoryService = inventoryService;
    }

    public FetchProductResponseView getProductsByType(FetchProductRequest request) {
        if (FetchProductRequestTypeEnum.BEST_SELLER.getType().equalsIgnoreCase(request.getType())) {
            return fetchBestSellerDefaultView();
        }
        FetchProductResponse inventoryFetchProductResponse = inventoryService.getFetchProductResponse(InventoryAdapter.getInventoryRequest(request));
        return InventoryAdapter.getFetchProductResponseViewFromInventoryFetchProductResponse(inventoryFetchProductResponse);
    }

    private FetchProductResponseView fetchBestSellerDefaultView() {
        List<FetchProductResponseView.ProductView> productViews = List.of(
                FetchProductResponseView.ProductView.builder()
                        .productId("1")
                        .productName("Coconut Oil")
                        .price(250.0)
                        .rating(4.5f)
                        .reviewCount(120)
                        .categoryId("oil")
                        .variations(Arrays.asList(
                                FetchProductResponseView.VariationView.builder()
                                        .variation("500ml")
                                        .images(List.of("https://drive.google.com/file/d/1ysLZmneEIGt5ZpzQjrLRZzIXfOFEC5_N/view?usp=sharing"))
                                        .build(),
                                FetchProductResponseView.VariationView.builder()
                                        .variation("1L")
                                        .images(List.of("https://drive.google.com/file/d/1ysLZmneEIGt5ZpzQjrLRZzIXfOFEC5_N/view?usp=sharing"))
                                        .build()
                        ))
                        .actions(Arrays.asList(
                                FetchProductResponseView.ActionView.builder()
                                        .action("BUY")
                                        .buttonText("Add to Cart")
                                        .build(),
                                FetchProductResponseView.ActionView.builder()
                                        .action("WISHLIST")
                                        .buttonText("Save for Later")
                                        .build()
                        ))
                        .build(),
                FetchProductResponseView.ProductView.builder()
                        .productId("2")
                        .productName("Groundnut Oil")
                        .price(300.0)
                        .rating(4.7f)
                        .reviewCount(200)
                        .categoryId("oil")
                        .variations(Arrays.asList(
                                FetchProductResponseView.VariationView.builder()
                                        .variation("500ml")
                                        .images(List.of("https://drive.google.com/file/d/1OQpiPl1dBTToxyH80iMoNwIxOA9ntHoh/view?usp=sharing"))
                                        .build(),
                                FetchProductResponseView.VariationView.builder()
                                        .variation("1L")
                                        .images(List.of("https://drive.google.com/file/d/1OQpiPl1dBTToxyH80iMoNwIxOA9ntHoh/view?usp=sharing"))
                                        .build()
                        ))
                        .actions(Arrays.asList(
                                FetchProductResponseView.ActionView.builder()
                                        .action("BUY")
                                        .buttonText("Add to Cart")
                                        .build(),
                                FetchProductResponseView.ActionView.builder()
                                        .action("WISHLIST")
                                        .buttonText("Save for Later")
                                        .build()
                        ))
                        .build(),
                FetchProductResponseView.ProductView.builder()
                        .productId("3")
                        .productName("Sesame Oil")
                        .price(400.0)
                        .rating(4.8f)
                        .reviewCount(150)
                        .categoryId("oil")
                        .variations(Arrays.asList(
                                FetchProductResponseView.VariationView.builder()
                                        .variation("500ml")
                                        .images(List.of("https://drive.google.com/file/d/1tOs2jYkcG4eWj_zgapArr6hZFssYIZ_M/view?usp=sharing"))
                                        .build(),
                                FetchProductResponseView.VariationView.builder()
                                        .variation("1L")
                                        .images(List.of("https://drive.google.com/file/d/1tOs2jYkcG4eWj_zgapArr6hZFssYIZ_M/view?usp=sharing"))
                                        .build()
                        ))
                        .actions(Arrays.asList(
                                FetchProductResponseView.ActionView.builder()
                                        .action("BUY")
                                        .buttonText("Add to Cart")
                                        .build(),
                                FetchProductResponseView.ActionView.builder()
                                        .action("WISHLIST")
                                        .buttonText("Save for Later")
                                        .build()
                        ))
                        .build()
        );

        return FetchProductResponseView.builder()
                .productItems(productViews)
                .build();
    }
}
