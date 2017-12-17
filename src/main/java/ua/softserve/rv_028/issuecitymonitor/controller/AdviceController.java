package ua.softserve.rv_028.issuecitymonitor.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.softserve.rv_028.issuecitymonitor.Constants;
import ua.softserve.rv_028.issuecitymonitor.exception.RegistrationException;

@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = Logger.getLogger(AdviceController.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public void handleNotFound(Exception e){
        LOGGER.error(e.getMessage());
    }


    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Constants.REGISTRATION_FAIL_REASON)
    @ExceptionHandler(RegistrationException.class)
    public void registrationError(RegistrationException e){
        LOGGER.error(e.getMessage());
    }
}

