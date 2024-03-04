package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BankControllerAdvice
{
    @ExceptionHandler(value={InvalidPasswordException.class})
    public ResponseEntity<String> invalidPasswordException(InvalidPasswordException e){
    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
}

}
