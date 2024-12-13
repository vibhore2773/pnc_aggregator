package com.pureandcold.aggregator.services.external;

import com.fasterxml.jackson.databind.util.ExceptionUtil;
import com.pureandcold.aggregator.model.internal.requests.ResetPasswordRequest;
import com.pureandcold.aggregator.model.internal.requests.ForgetPasswordRequest;
import com.pureandcold.aggregator.model.internal.requests.ResendOtpRequest;
import com.pureandcold.aggregator.model.internal.requests.UserLoginRequest;
import com.pureandcold.aggregator.model.internal.responses.ForgetPasswordResponse;
import com.pureandcold.aggregator.model.internal.responses.ResendOtpResponse;
import com.pureandcold.aggregator.model.internal.responses.ResetPasswordResponse;
import com.pureandcold.aggregator.model.internal.responses.UserLoginResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pureandcold.aggregator.model.external.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.external.requests.VerifyOtpRequest;
import com.pureandcold.aggregator.model.external.responses.UserRegistrationResponse;
import com.pureandcold.aggregator.model.external.responses.VerifyOtpResponse;
import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderService.*;

@Component
@Slf4j
public class UserOrderService {
    private final RestTemplate restTemplate;

    @Value(USER_ORDER_BASE_URL)
    private String userOrderBaseUrl;

    UserOrderService(@Qualifier("userOrderServiceRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public UserRegistrationResponse registerUser(UserRegistrationRequest registrationRequest) {
        String uri = userOrderBaseUrl.concat(USER_REGISTRATION_API_PATH);
        try {
            HttpEntity<UserRegistrationRequest> requestEntity = new HttpEntity<>(registrationRequest);

            ResponseEntity<UserRegistrationResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    UserRegistrationResponse.class
            );

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {

        }
        return new UserRegistrationResponse(false, "Registration failed"); 
    }

    public VerifyOtpResponse verifyOtp(VerifyOtpRequest verifyOtpDownstreamRequest) {
        String uri = userOrderBaseUrl.concat(VERIFY_OTP_API_PATH);
        try {
            HttpEntity<VerifyOtpRequest> requestEntity = new HttpEntity<>(verifyOtpDownstreamRequest);

            ResponseEntity<VerifyOtpResponse> responseEntity = restTemplate.exchange(
                uri,
                HttpMethod.POST,
                requestEntity,
                VerifyOtpResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception Occurred While Verifying User. Stacktrace : {}", ExceptionUtils.getStackTrace(e));
        }
        return new VerifyOtpResponse(false, "Registration failed"); 
    }

    public UserLoginResponse login(UserLoginRequest userRequest) {
        String uri = userOrderBaseUrl.concat(USER_LOGIN_API_PATH);
        try {
            HttpEntity<UserLoginRequest> requestEntity = new HttpEntity<>(userRequest);
            ResponseEntity<UserLoginResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    UserLoginResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception Occurred While Trying User Login. Stacktrace : {}", ExceptionUtils.getStackTrace(e));
        }
        return new UserLoginResponse(false,"Login Failed", null);
    }

    public ResendOtpResponse resendOtp(ResendOtpRequest resendOtpRequest) {
        String uri = userOrderBaseUrl.concat(RESEND_OTP_API_PATH);
        try {
            HttpEntity<ResendOtpRequest> requestEntity = new HttpEntity<>(resendOtpRequest);
            ResponseEntity<ResendOtpResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    ResendOtpResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception Occurred While Trying To Resend Otp. Stacktrace : {}", ExceptionUtils.getStackTrace(e));
        }
        return new ResendOtpResponse(false,"Resend Otp Failed");
    }

    public ForgetPasswordResponse forgetPassword(ForgetPasswordRequest forgetPasswordRequest) {
        String uri = userOrderBaseUrl.concat(FORGET_PASSWORD_API_PATH);
        try {
            HttpEntity<ForgetPasswordRequest> requestEntity = new HttpEntity<>(forgetPasswordRequest);
            ResponseEntity<ForgetPasswordResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    ForgetPasswordResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception Occurred While Trying To Create Forget Password Request. Stacktrace : {}", ExceptionUtils.getStackTrace(e));
        }
        return new ForgetPasswordResponse(false,"Forget Password Request Failed");
    }

    public ResetPasswordResponse resetPassword(ResetPasswordRequest resetPasswordRequest) {
        String uri = userOrderBaseUrl.concat(RESET_PASSWORD_API_PATH);
        try {
            HttpEntity<ResetPasswordRequest> requestEntity = new HttpEntity<>(resetPasswordRequest);
            ResponseEntity<ResetPasswordResponse> responseEntity = restTemplate.exchange(
                    uri,
                    HttpMethod.POST,
                    requestEntity,
                    ResetPasswordResponse.class);
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            log.error("Exception Occurred While Trying To Create Reset Password Request. Stacktrace : {}", ExceptionUtils.getStackTrace(e));
        }
        return new ResetPasswordResponse(false,"Forget Password Request Failed");
    }
}
