package com.companyname.GasBookingSystem.payment;

import com.companyname.GasBookingSystem.customer.Exception.ViewCustomerProfileException;
import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.payment.Exception.PaymentException;
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
    public ResponseEntity<String> addCylinderExceptiom(AddCylinderException e){
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);

    }

}
