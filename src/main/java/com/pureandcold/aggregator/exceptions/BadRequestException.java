package com.pureandcold.aggregator.exceptions;


public class BadRequestException extends Exception {
    public BadRequestException(String message) {
        this.message = message;
    }

    String message;
}
