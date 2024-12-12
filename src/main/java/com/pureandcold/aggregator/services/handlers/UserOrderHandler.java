package com.pureandcold.aggregator.services.handlers;

import java.util.Objects;

import com.pureandcold.aggregator.model.external.requests.ForgetPasswordRequest;
import com.pureandcold.aggregator.model.external.requests.ResendOtpRequest;
import com.pureandcold.aggregator.model.external.requests.UserLoginRequest;
import com.pureandcold.aggregator.model.external.responses.*;
import com.pureandcold.aggregator.model.internal.responses.*;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.pureandcold.aggregator.exceptions.BadRequestException;
import com.pureandcold.aggregator.model.internal.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.internal.requests.VerifyOtpRequest;
import com.pureandcold.aggregator.services.adapters.UserOrderServiceAdapter;
import com.pureandcold.aggregator.services.external.UserOrderService;

@Service
public class UserOrderHandler {
    
    private final UserOrderService userOrderService;

    UserOrderHandler(UserOrderService userOrderService) {
        this.userOrderService = userOrderService;
    }

    public UserRegistrationResponseView getUserRegistrationResponse (UserRegistrationRequest userRegistrationRequest) throws BadRequestException {
        validate(userRegistrationRequest);
        UserRegistrationResponse userRegistrationResponse = userOrderService.registerUser(UserOrderServiceAdapter.getUserRegistrationServiceRequest(userRegistrationRequest));
        return UserOrderServiceAdapter.getResponseViewFromResponse(userRegistrationResponse);
    }

private void validate(UserRegistrationRequest userRegistrationRequest) throws BadRequestException {
    if (userRegistrationRequest.getUsername() == null ||
        userRegistrationRequest.getEmail() == null ||
        userRegistrationRequest.getPhoneNumber() == null ||
        userRegistrationRequest.getPassword() == null ||
        userRegistrationRequest.getConfirmPassword() == null) {
        throw new BadRequestException("Parameters cannot be null");
    }
}

public VerifyOtpResponseView verifyOtp(VerifyOtpRequest request) throws BadRequestException {
    validateVerifyOtpRequest(request);
    VerifyOtpResponse verifyOtpResponse = userOrderService.verifyOtp(UserOrderServiceAdapter.getVerifyOtpDownstreamRequest(request));

    return UserOrderServiceAdapter.getVerifyOtpResponseView(verifyOtpResponse);
    
}

private void validateVerifyOtpRequest(VerifyOtpRequest request) throws BadRequestException {
    if (Objects.isNull(request) || 
    (!StringUtils.hasLength(request.getEmail()) && !StringUtils.hasText(request.getPhoneNumber())) ||
    !StringUtils.hasText(request.getOtp())) {
        throw new BadRequestException("Invalid Request");
    }
}

    public UserLoginResponseView loginUser(UserLoginRequest userRequest) throws BadRequestException {
        validateLoginRequest(userRequest);
        UserLoginResponse userLoginResponse = userOrderService.login(UserOrderServiceAdapter.getUserLoginRequest(userRequest));
        return UserOrderServiceAdapter.getUserLoginResponseView(userLoginResponse);
    }

    private void validateLoginRequest(UserLoginRequest userRequest) throws BadRequestException{
        if (!StringUtils.hasLength(userRequest.getUsername()) &&
        !StringUtils.hasLength(userRequest.getPassword())) {
            throw new BadRequestException("Username or Password cannot be null");
        }
    }

    public ResendOtpResponseView resendOtp(ResendOtpRequest resendOtpRequest) throws BadRequestException {
        validateResendOtpRequest(resendOtpRequest);
        ResendOtpResponse resendOtpResponse = userOrderService.resendOtp(UserOrderServiceAdapter.getResendOtpRequest(resendOtpRequest));
        return UserOrderServiceAdapter.getResendOtpResponseView(resendOtpResponse);
    }

    private void validateResendOtpRequest(ResendOtpRequest resendOtpRequest) throws BadRequestException {
        if (!StringUtils.hasLength(resendOtpRequest.getEmail()) && !StringUtils.hasLength(resendOtpRequest.getPhoneNumber())) {
            throw new BadRequestException("Both Email & PhoneNumber Can't Be Null");
        }
    }

    public ForgetPasswordResponseView forgetPassword(ForgetPasswordRequest request) throws BadRequestException {
        validateForgetPasswordRequest(request);
        ForgetPasswordResponse forgetPasswordResponse = userOrderService.forgetPassword(UserOrderServiceAdapter.getForgetPasswordRequest(request));
        return UserOrderServiceAdapter.getForgetPasswordResponseView(forgetPasswordResponse);
    }

    private void validateForgetPasswordRequest(ForgetPasswordRequest request) throws BadRequestException {
        if (!StringUtils.hasLength(request.getEmail()) && !StringUtils.hasLength(request.getPhoneNumber())) {
            throw new BadRequestException("Both Email & PhoneNumber Can't Be Null");
        }
    }
}
