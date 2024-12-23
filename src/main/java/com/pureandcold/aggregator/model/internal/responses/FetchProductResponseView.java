package com.pureandcold.aggregator.model.internal.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class FetchProductResponseView {
    
    private List<ProductView> productItems;

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ProductView {
        private String productId;
        private String productName;
        private Double price;
        private Float rating;
        private Integer reviewCount;
        private String categoryId;
        private List<VariationView> variations;
        private List<ActionView> actions;
    }

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class VariationView {
        private String variation;
        private List<String> images;
    }

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class ActionView {
        private String action;
        private String buttonText;
    }

}
