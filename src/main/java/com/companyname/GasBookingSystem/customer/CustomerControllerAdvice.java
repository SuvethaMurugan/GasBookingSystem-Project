package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.Exception.InvalidEmailException;
import com.companyname.GasBookingSystem.customer.Exception.InvalidPasswordException;
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
    public ResponseEntity<String> customerException(CustomerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value={InvalidPasswordException.class})
    public ResponseEntity<String> invalidPasswordException(InvalidPasswordException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value={InvalidEmailException.class})
    public ResponseEntity<String> invalidEmailException(InvalidEmailException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
