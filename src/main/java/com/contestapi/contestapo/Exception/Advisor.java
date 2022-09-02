package com.contestapi.contestapo.Exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class Advisor {

    @ExceptionHandler(CustomError.class)
    public ResponseEntity<CustomError> handleException(CustomError ex, WebRequest mm) {
        CustomError out=new CustomError(ex.getError(),ex.getDescription());
       return new ResponseEntity<CustomError>(out, HttpStatus.BAD_REQUEST);
    }
}
