package com.pureandcold.aggregator.services.adapters;

import org.springframework.stereotype.Component;

import com.pureandcold.aggregator.model.external.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.external.responses.UserRegistrationResponse;
import com.pureandcold.aggregator.model.external.responses.VerifyOtpResponse;
import com.pureandcold.aggregator.model.internal.requests.VerifyOtpRequest;
import com.pureandcold.aggregator.model.internal.responses.UserRegistrationResponseView;
import com.pureandcold.aggregator.model.internal.responses.VerifyOtpResponseView;

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

    public static com.pureandcold.aggregator.model.external.requests.VerifyOtpRequest getVerifyOtpDownstreamRequest(VerifyOtpRequest request) {
        return com.pureandcold.aggregator.model.external.requests.VerifyOtpRequest.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber())
                .otp(request.getOtp())
                .build();
    }

    public static VerifyOtpResponseView getVerifyOtpResponseView(VerifyOtpResponse verifyOtpResponse) {
        return VerifyOtpResponseView.builder()
                .success(verifyOtpResponse.isSuccess())
                .message(verifyOtpResponse.getMessage())
                .build();
    }
}
