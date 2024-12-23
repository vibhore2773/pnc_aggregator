package com.pureandcold.aggregator.services.adapters;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.pureandcold.aggregator.model.external.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse.Action;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse.Product;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse.Variation;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView.ActionView;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView.ProductView;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView.VariationView;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class InventoryAdapter {
    
    public static FetchProductRequest getInventoryRequest(com.pureandcold.aggregator.model.internal.requests.FetchProductRequest request) {
        return FetchProductRequest.builder().type(request.getType()).build();
    }

    public static FetchProductResponseView getFetchProductResponseViewFromInventoryFetchProductResponse(FetchProductResponse inventoryFetchProductResponse) {
        List <ProductView> productViews =  new ArrayList<>();
        for (Product product : inventoryFetchProductResponse.getProductItems()) {
            ProductView productView = ProductView.builder()
            .productId(product.getProductId())
            .productName(product.getProductName())
            .price(product.getPrice())
            .rating(product.getRating())
            .reviewCount(product.getReviewCount())
            .categoryId(product.getCategoryId())
            .variations(getVariationsViewFromInventoryProductVariationResponse(product.getVariations()))
            .actions(getActionsViewFromInventoryProductActionResponse(product.getActions())).build();
            productViews.add(productView);
        }
        
        return FetchProductResponseView.builder().productItems(productViews).build();
    }


    private static List<ActionView> getActionsViewFromInventoryProductActionResponse(List<Action> actions) {
        List<ActionView> actionViews = new ArrayList<>();
        for (Action action : actions) {
            ActionView actionView = ActionView.builder()
                                        .action(action.getAction())
                                        .buttonText(action.getButtonText() ).build();
            actionViews.add(actionView);
        }
        return actionViews;
    }


    private static List<VariationView> getVariationsViewFromInventoryProductVariationResponse(List<Variation> variations) {

        List<VariationView> variationViews = new ArrayList<>();
        for (Variation variation : variations) {
            VariationView variationView = VariationView.builder()
                                            .variation(variation.getVariation())
                                            .images(variation.getImages()).build();
            variationViews.add(variationView);
        }
        return variationViews;
    
    }

}
