package com.pureandcold.aggregator.services.external;

import static com.pureandcold.aggregator.constants.HttpConstants.InventoryService.FETCH_PRODUCT_API_PATH;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import com.pureandcold.aggregator.model.external.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.external.responses.FetchProductResponse;
import com.pureandcold.aggregator.constants.HttpConstants;

@Component
public class InventoryService {
    private final RestTemplate restTemplate;

    @Value(HttpConstants.InventoryService.INVENTORY_BASE_URL)
    private static String INVENTORY_BASE_URL;

    public InventoryService(@Qualifier("inventoryRestTemplate") RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public FetchProductResponse getFetchProductResponse(FetchProductRequest request) {
        String uri = UriComponentsBuilder.fromHttpUrl(INVENTORY_BASE_URL.concat(FETCH_PRODUCT_API_PATH))
                .queryParam("type", request.getType())
                .toUriString();

        try {
            ResponseEntity<FetchProductResponse> responseEntity = restTemplate.exchange(
                    uri, 
                    HttpMethod.GET, 
                    null, 
                    FetchProductResponse.class);

            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            }
        } catch (Exception e) {
            // Log the exception if necessary
            e.printStackTrace(); // Optionally log the exception
        }
        return new FetchProductResponse(); 
    }

}
