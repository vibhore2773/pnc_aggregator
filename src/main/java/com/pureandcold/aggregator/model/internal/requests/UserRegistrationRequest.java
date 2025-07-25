package com.pureandcold.aggregator.model.internal.requests;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies.SnakeCaseStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonNaming(SnakeCaseStrategy.class)
public class UserRegistrationRequest {
    private String username;
    private String email;
    private String phoneNumber;
    private String password;
    private String confirmPassword;
}
