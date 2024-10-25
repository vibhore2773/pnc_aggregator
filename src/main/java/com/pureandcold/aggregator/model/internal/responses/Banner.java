package com.pureandcold.aggregator.model.internal.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class Banner {
    private String imageUrl;
    private String altText;
    private String redirecterUrl;
}
