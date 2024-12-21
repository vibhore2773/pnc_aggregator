package com.pureandcold.aggregator.controllers;

import com.pureandcold.aggregator.model.external.requests.ForgetPasswordRequest;
import com.pureandcold.aggregator.model.external.requests.ResendOtpRequest;
import com.pureandcold.aggregator.model.external.requests.ResetPasswordRequest;
import com.pureandcold.aggregator.model.external.requests.UserLoginRequest;
import com.pureandcold.aggregator.model.external.responses.ForgetPasswordResponseView;
import com.pureandcold.aggregator.model.external.responses.ResendOtpResponseView;
import com.pureandcold.aggregator.model.external.responses.ResetPasswordResponseView;
import com.pureandcold.aggregator.model.external.responses.UserLoginResponseView;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pureandcold.aggregator.exceptions.BadRequestException;
import com.pureandcold.aggregator.model.internal.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.internal.requests.VerifyOtpRequest;
import com.pureandcold.aggregator.model.internal.responses.UserRegistrationResponseView;
import com.pureandcold.aggregator.model.internal.responses.VerifyOtpResponseView;
import com.pureandcold.aggregator.services.handlers.UserOrderHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderController.*;


@RestController
@RequestMapping(BASE_PATH)
public class UserOrderController {
    
    private UserOrderHandler userOrderHandler;

    UserOrderController(UserOrderHandler userOrderHandler) {
        this.userOrderHandler = userOrderHandler;
    }

    @PostMapping(USER_REGISTRATION_PATH)
    public ResponseEntity<UserRegistrationResponseView> registerUser(@RequestBody UserRegistrationRequest request) {
        UserRegistrationResponseView response = null;
        try {
            response = userOrderHandler.getUserRegistrationResponse(request);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(VERIFY_OPT_PATH)
    public ResponseEntity<VerifyOtpResponseView> verifyOtp(@RequestBody VerifyOtpRequest request) {
        VerifyOtpResponseView response = null;
        try {
            response = userOrderHandler.verifyOtp(request);
        } catch (BadRequestException e) {
            return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(LOGIN_PATH)
    public ResponseEntity<UserLoginResponseView> login(@RequestBody UserLoginRequest userRequest) {
        UserLoginResponseView response = null;
        try {
            response = userOrderHandler.loginUser(userRequest);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(RESEND_OTP_PATH)
    public ResponseEntity<ResendOtpResponseView> resendOtp(@RequestBody ResendOtpRequest request) {
        ResendOtpResponseView response = null;
        try {
            response = userOrderHandler.resendOtp(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(FORGET_PASSWORD_PATH)
    public ResponseEntity<ForgetPasswordResponseView> forgotPassword(@RequestBody ForgetPasswordRequest request) {
        ForgetPasswordResponseView response = null;
        try {
            response = userOrderHandler.forgetPassword(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(RESET_PASSWORD_PATH)
    public ResponseEntity<ResetPasswordResponseView> resetPassword(@RequestBody ResetPasswordRequest request) {
        ResetPasswordResponseView response = null;
        try {
            response = userOrderHandler.resetPassword(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
