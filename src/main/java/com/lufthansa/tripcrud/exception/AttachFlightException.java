package com.lufthansa.tripcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Cannot add flight to a non Approved trip")

public class AttachFlightException extends Exception {

    public AttachFlightException(String message) {
        super("Cannot add flight to a non Approved trip");
    }
}
