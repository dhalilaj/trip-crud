package com.lufthansa.tripcrud.exception;

import com.lufthansa.tripcrud.dto.ResponseMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(TripNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg tripDoesNotExistException() {
        logger.error("Trip Does Not Exist");
        return new ResponseMsg("Trip Does Not Exist");
    }

    @ExceptionHandler(FlightNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ResponseMsg flightDoesNotExistException() {
        logger.error("Flight Does Not Exist");
        return new ResponseMsg("Flight Does Not Exist");
    }

    @ExceptionHandler(AttachFlightException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMsg attachFlightException(AttachFlightException attachFlightException) {
        logger.error(attachFlightException.getMessage());
        return new ResponseMsg(attachFlightException.getMessage());
    }

    @ExceptionHandler(NoPermissionException.class)
    @ResponseStatus(value = HttpStatus.UNAUTHORIZED)
    @ResponseBody
    public ResponseMsg noPermissionException(NoPermissionException noPermissionException) {
        logger.error(noPermissionException.getMessage());
        return new ResponseMsg(noPermissionException.getMessage());
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ResponseMsg runtimeException(RuntimeException runtimeException) {
        logger.error(runtimeException.getMessage());
        return new ResponseMsg(runtimeException.getMessage());
    }



}
