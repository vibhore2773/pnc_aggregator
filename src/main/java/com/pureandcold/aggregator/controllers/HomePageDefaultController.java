package com.pureandcold.aggregator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pureandcold.aggregator.constants.HttpConstants.DefaultController;
import com.pureandcold.aggregator.model.internal.requests.FetchProductRequest;
import com.pureandcold.aggregator.model.internal.responses.FetchProductResponseView;
import com.pureandcold.aggregator.model.internal.responses.FooterResponse;
import com.pureandcold.aggregator.model.internal.responses.HeaderResponse;
import com.pureandcold.aggregator.model.internal.responses.WidgetsResponse;
import com.pureandcold.aggregator.services.handlers.HomePageAndInfoDefaultHandler;

import lombok.extern.slf4j.Slf4j;


@RestController
@Slf4j
@RequestMapping(DefaultController.BASE_PATH)
public class HomePageDefaultController {

    private final HomePageAndInfoDefaultHandler homePageDefaultHandler;

    HomePageDefaultController(HomePageAndInfoDefaultHandler homePageDefaultHandler) {
        this.homePageDefaultHandler = homePageDefaultHandler;
    }

    @RequestMapping(value = DefaultController.HEADER_API_PATH, method = RequestMethod.GET)
    public ResponseEntity<HeaderResponse> getHeader() {
        HeaderResponse response = null;
        try {
            log.info("log 2");
            response = homePageDefaultHandler.getHeaderResponse();
        } catch (Exception e) {
            // TODO :: check why error not working
            // log.error( "Exception Occurred while getting default header. Stack trace : {}", e.printStackTrace());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(DefaultController.FOOTER_API_PATH)
    public ResponseEntity<FooterResponse> getFooter() {
        FooterResponse response = null;
        try {
            response = homePageDefaultHandler.getFooterResponse();
        } catch (Exception e) {
            // TODO :: check why error not working
            // log.error( "Exception Occurred while getting default header. Stack trace : {}", e.printStackTrace());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(DefaultController.WIDGETS_API_PATH)
    public ResponseEntity<WidgetsResponse> getWidgetsResponse() {
        WidgetsResponse response = null;
        try {
            response = homePageDefaultHandler.getWidgetsResponse();
        } catch (Exception e) {
            // TODO :: check why error not working
            // log.error( "Exception Occurred while getting default header. Stack trace : {}", e.printStackTrace());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping(DefaultController.GET_PRODUCTS_PATH)
    public ResponseEntity<FetchProductResponseView> getProducts(@RequestParam(name = "type", required = true) String type) {
        FetchProductRequest request = FetchProductRequest.builder().type(type).build();
        FetchProductResponseView response = null;
        try {
            response = homePageDefaultHandler.getProductsByType(request);
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
