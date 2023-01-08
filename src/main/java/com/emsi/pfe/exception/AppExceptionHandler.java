package com.emsi.pfe.exception;

import com.emsi.pfe.security.SecurityConstants;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class AppExceptionHandler {
    @ExceptionHandler(value=ReservationAlreadyDoneException.class)
    public ResponseEntity<Object> HandleReservationAlreadyDoneException(ReservationAlreadyDoneException ex, WebRequest request){
        Map<String,String> errors=new HashMap<>();
        errors.put(SecurityConstants.ERROR,SecurityConstants.RESERVATION_ALREADY_DONE);
        return new ResponseEntity<>(errors,new HttpHeaders(), HttpStatus.BAD_REQUEST);
    }
}
