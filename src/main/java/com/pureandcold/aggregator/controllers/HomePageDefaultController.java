package com.pureandcold.aggregator.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pureandcold.aggregator.constants.HttpConstants.DefaultController;
import com.pureandcold.aggregator.model.internal.responses.FooterResponse;
import com.pureandcold.aggregator.model.internal.responses.HeaderResponse;
import com.pureandcold.aggregator.model.internal.responses.WidgetsResponse;
import com.pureandcold.aggregator.services.handlers.HomePageDefaultHandler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@Slf4j
@RequestMapping(DefaultController.BASE_PATH)
public class HomePageDefaultController {

    private final HomePageDefaultHandler homePageDefaultHandler;

    HomePageDefaultController(HomePageDefaultHandler homePageDefaultHandler) {
        this.homePageDefaultHandler = homePageDefaultHandler;
    }

    @GetMapping(DefaultController.HEADER_API_PATH)
    public ResponseEntity<HeaderResponse> getHeader() {
        HeaderResponse response = null;
        try {
            response = homePageDefaultHandler.getHeaderResponse();
        } catch (Exception e) {
            // TODO :: check why error not working
            // log.error( "Exception Occurred while getting default header. Stack trace : {}", e.printStackTrace());
            return new ResponseEntity<>(response,HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
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
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }

    @GetMapping(DefaultController.WIDGETS_API_PATH)
    public ResponseEntity<WidgetsResponse> getWidgetsResponse(@RequestParam String param) {
        WidgetsResponse response = null;
        try {
            response = homePageDefaultHandler.getWidgetsResponse();
        } catch (Exception e) {
            // TODO :: check why error not working
            // log.error( "Exception Occurred while getting default header. Stack trace : {}", e.printStackTrace());
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response,HttpStatus.ACCEPTED);
    }
}
