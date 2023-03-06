package com.lufthansa.tripcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Flight Does Not Exist")
public class FlightNotFoundException extends RuntimeException {

    public FlightNotFoundException(Long id) {
        super("Flight id : " + id + " does not exist");
    }
}
