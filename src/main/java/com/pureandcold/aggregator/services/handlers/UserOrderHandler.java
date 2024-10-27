package com.pureandcold.aggregator.services.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.BadRequest;
import org.springframework.web.server.ResponseStatusException;

import com.pureandcold.aggregator.exceptions.BadRequestException;
import com.pureandcold.aggregator.model.external.responses.UserRegistrationResponse;
import com.pureandcold.aggregator.model.internal.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.internal.responses.UserRegistrationResponseView;
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
}
