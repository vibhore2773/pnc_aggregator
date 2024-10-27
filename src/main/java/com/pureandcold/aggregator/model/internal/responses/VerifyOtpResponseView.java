package com.pureandcold.aggregator.model.internal.responses;

import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
@JsonNaming(SnakeCaseStrategy.class)
public class VerifyOtpResponseView {
    private boolean success;
    private String message;
}
