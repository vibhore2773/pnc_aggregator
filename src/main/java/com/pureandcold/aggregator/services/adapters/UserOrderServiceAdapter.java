package com.pureandcold.aggregator.services.adapters;

import org.springframework.stereotype.Component;

import com.pureandcold.aggregator.model.external.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.external.responses.UserRegistrationResponse;
import com.pureandcold.aggregator.model.internal.responses.UserRegistrationResponseView;

@Component
public class UserOrderServiceAdapter {


    public static UserRegistrationRequest getUserRegistrationServiceRequest(com.pureandcold.aggregator.model.internal.requests.UserRegistrationRequest registrationRequest) {
        return UserRegistrationRequest.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .password(registrationRequest.getPassword())
                .confirmPassword(registrationRequest.getConfirmPassword())
                .build();
    }

    public static UserRegistrationResponseView getResponseViewFromResponse(UserRegistrationResponse userRegistrationResponse) {
        return UserRegistrationResponseView.builder()
                .success(userRegistrationResponse.isSuccess())
                .message(userRegistrationResponse.getMessage())
                .build();
    }
}
