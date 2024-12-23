package com.pureandcold.aggregator.model.external.responses;


import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@JsonNaming(SnakeCaseStrategy.class)
public class FetchProductResponse {
    
    private List<Product> productItems;

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class Product {
        private String productId;
        private String productName;
        private Double price;
        private Float rating;
        private Integer reviewCount;
        private String categoryId;
        private List<Variation> variations;
        private List<Action> actions;
    }

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class Variation {
        private String variation;
        private List<String> images;
    }

    @Data
    @Builder
    @JsonNaming(SnakeCaseStrategy.class)
    public static class Action {
        private String action;
        private String buttonText;
    }

}

