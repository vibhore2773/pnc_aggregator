package com.pureandcold.aggregator.services.external;

import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderService.USER_ORDER_BASE_URL;
import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderService.USER_REGISTRATION_API_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.UserOrderService.VERIFY_OTP_API_PATH;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.pureandcold.aggregator.model.external.requests.UserRegistrationRequest;
import com.pureandcold.aggregator.model.external.requests.VerifyOtpRequest;
import com.pureandcold.aggregator.model.external.responses.UserRegistrationResponse;
import com.pureandcold.aggregator.model.external.responses.VerifyOtpResponse;

@Component
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
            e.printStackTrace();
            // Optionally, you could log the error or throw a custom exception
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
            e.printStackTrace();
            // Optionally, you could log the error or throw a custom exception
        }
        return new VerifyOtpResponse(false, "Registration failed"); 
    }
}
