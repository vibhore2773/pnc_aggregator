package com.pureandcold.aggregator.controllers;

import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderController.BASE_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderController.USER_REGISTRATION_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderController.VERIFY_OPT_PATH;

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
        }catch (BadRequestException e) {
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
    
}
