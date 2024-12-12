package com.pureandcold.aggregator.services.adapters;

import com.pureandcold.aggregator.constants.OtpRequestFlowType;
import com.pureandcold.aggregator.model.external.requests.ForgetPasswordRequest;
import com.pureandcold.aggregator.model.external.requests.ResendOtpRequest;
import com.pureandcold.aggregator.model.external.requests.UserLoginRequest;
import com.pureandcold.aggregator.model.external.responses.*;
import com.pureandcold.aggregator.model.internal.responses.*;
import org.springframework.stereotype.Component;

import com.pureandcold.aggregator.model.external.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.internal.requests.VerifyOtpRequest;

@Component
public class UserOrderServiceAdapter {


    public static UserRegistrationRequest getUserRegistrationServiceRequest(com.pureandcold.aggregator.model.internal.requests.UserRegistrationRequest registrationRequest) {
        return UserRegistrationRequest.builder()
                .username(registrationRequest.getUsername())
                .email(registrationRequest.getEmail())
                .phoneNumber(registrationRequest.getPhoneNumber())
                .password(registrationRequest.getPassword())
                .confirmPassword(registrationRequest.getConfirmPassword())
                .countryCode("IN")
                .flowType(OtpRequestFlowType.SMS.name())
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

    public static com.pureandcold.aggregator.model.internal.requests.UserLoginRequest getUserLoginRequest(UserLoginRequest userRequest) {
        return com.pureandcold.aggregator.model.internal.requests.UserLoginRequest.builder()
                .username(userRequest.getUsername())
                .password(userRequest.getPassword()).build();
    }

    public static UserLoginResponseView getUserLoginResponseView(UserLoginResponse userLoginResponse) {
        return UserLoginResponseView.builder()
                .success(userLoginResponse.isSuccess())
                .message(userLoginResponse.getMessage())
                .token(userLoginResponse.getToken()).build();
    }

    public static com.pureandcold.aggregator.model.internal.requests.ResendOtpRequest getResendOtpRequest(ResendOtpRequest resendOtpRequest) {
        return com.pureandcold.aggregator.model.internal.requests.ResendOtpRequest.builder()
                .email(resendOtpRequest.getEmail())
                .phoneNumber(resendOtpRequest.getPhoneNumber()).build();
    }

    public static ResendOtpResponseView getResendOtpResponseView(ResendOtpResponse resendOtpResponse) {
        return ResendOtpResponseView.builder()
                .success(resendOtpResponse.isSuccess())
                .message(resendOtpResponse.getMessage()).build();
    }

    public static com.pureandcold.aggregator.model.internal.requests.ForgetPasswordRequest getForgetPasswordRequest(ForgetPasswordRequest request) {
        return com.pureandcold.aggregator.model.internal.requests.ForgetPasswordRequest.builder()
                .email(request.getEmail())
                .phoneNumber(request.getPhoneNumber()).build();
    }

    public static ForgetPasswordResponseView getForgetPasswordResponseView(ForgetPasswordResponse forgetPasswordResponse) {
        return ForgetPasswordResponseView.builder()
                .message(forgetPasswordResponse.getMessage())
                .success(forgetPasswordResponse.isSuccess()).build();
    }
}
