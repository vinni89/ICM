package ua.softserve.rv_028.issuecitymonitor.controller;

import org.apache.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import ua.softserve.rv_028.issuecitymonitor.Constants;
import ua.softserve.rv_028.issuecitymonitor.exception.RegistrationException;
import ua.softserve.rv_028.issuecitymonitor.exception.RestorePasswordException;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@ControllerAdvice
public class AdviceController {

    private static final Logger LOGGER = Logger.getLogger(AdviceController.class.getName());

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(IllegalStateException.class)
    public void handleNotFound(Exception e){
        LOGGER.error(e.getMessage());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public void handleArgumentException(Exception e){
        LOGGER.error(e.getMessage());
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = Constants.REGISTRATION_FAIL_REASON)
    @ExceptionHandler(RegistrationException.class)
    public void registrationError(RegistrationException e){
        LOGGER.error(e.getMessage());
    }

    @ExceptionHandler(RestorePasswordException.class)
    public void registrationError(RestorePasswordException e, HttpServletResponse response) throws IOException {
        LOGGER.error(e.getMessage());
        response.sendError(HttpStatus.BAD_REQUEST.value(), e.getMessage());
    }
}

