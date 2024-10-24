package com.pureandcold.aggregator.model.internal.responses;

import java.util.List;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class HeaderResponse {
    private String logoUrl;
    private List<NavigationItem> navigationItems;
}
