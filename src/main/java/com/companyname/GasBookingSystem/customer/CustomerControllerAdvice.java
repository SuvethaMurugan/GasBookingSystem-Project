package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

//import javax.security.auth.login.AccountException;

public class CustomerControllerAdvice {
    @ExceptionHandler(value={CustomerException.class})
    public ResponseEntity<String> AccountException(CustomerException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);

    }
}
