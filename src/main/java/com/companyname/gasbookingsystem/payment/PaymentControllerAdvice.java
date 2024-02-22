package com.companyname.gasbookingsystem.payment;

import com.companyname.gasbookingsystem.customer.exception.ViewCustomerProfileException;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.payment.exception.BankUpdateException;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class PaymentControllerAdvice {

    @ExceptionHandler(value={PaymentException.class})
    public ResponseEntity<String> paymentException(PaymentException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={ViewCustomerProfileException.class})
    public ResponseEntity<String> viewUserException(ViewCustomerProfileException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(value={AddCylinderException.class})
    public ResponseEntity<String> addCylinderException(AddCylinderException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={BankUpdateException.class})
    public ResponseEntity<String> bankUpdateException(BankUpdateException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
    }

}
