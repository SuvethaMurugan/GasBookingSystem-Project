package com.companyname.gasbookingsystem.customer.exception;

import com.companyname.gasbookingsystem.payment.exception.PaymentException;

public class ViewCustomerProfileException extends PaymentException {
    public ViewCustomerProfileException(String message){
        super(message);
    }
}
