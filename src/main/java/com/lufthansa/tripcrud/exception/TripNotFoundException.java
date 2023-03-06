package com.lufthansa.tripcrud.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Trip Does Not Exist")
public class TripNotFoundException extends Exception {

    public TripNotFoundException(Long id) {
        super("Trip id : " + id + " does not exist");
    }
}
