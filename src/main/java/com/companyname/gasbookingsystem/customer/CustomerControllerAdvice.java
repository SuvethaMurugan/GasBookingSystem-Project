package com.companyname.gasbookingsystem.customer;

import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
