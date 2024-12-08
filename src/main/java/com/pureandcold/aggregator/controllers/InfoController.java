package com.pureandcold.aggregator.controllers;

import static com.pureandcold.aggregator.constants.HttpConstants.InfoController.ABOUT_US_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.InfoController.BASE_PATH;
import static com.pureandcold.aggregator.constants.HttpConstants.InfoController.CONTACT_US_PATH;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

import com.pureandcold.aggregator.model.internal.responses.InfoResponse;
import com.pureandcold.aggregator.services.handlers.HomePageAndInfoDefaultHandler;

import lombok.extern.slf4j.Slf4j;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@RestController
@RequestMapping(BASE_PATH)
@Slf4j
public class InfoController {

    private final HomePageAndInfoDefaultHandler homePageAndInfoDefaultHandler;
    InfoController(HomePageAndInfoDefaultHandler homePageAndInfoDefaultHandler) {
        this.homePageAndInfoDefaultHandler = homePageAndInfoDefaultHandler;
    }

    @GetMapping(ABOUT_US_PATH)
    public ResponseEntity<InfoResponse> getAboutUsInfo() {
        InfoResponse response = null;
        try {
            log.info("log 1");
            response = homePageAndInfoDefaultHandler.getAboutUsInfo();
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping(CONTACT_US_PATH)
    public ResponseEntity<InfoResponse> getContactUsInfo() {
        InfoResponse response = null;
        try {
            response = homePageAndInfoDefaultHandler.getContactUsInfo();
        } catch (Exception e) {
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
