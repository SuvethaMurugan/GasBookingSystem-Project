package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomerControllerAdvice {
    @ExceptionHandler(value={CustomerException.class})
    public ResponseEntity<String> AccountException(CustomerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
}
