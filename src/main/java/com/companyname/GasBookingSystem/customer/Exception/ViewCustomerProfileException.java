package com.companyname.GasBookingSystem.customer.Exception;

import com.companyname.GasBookingSystem.payment.Exception.PaymentException;

public class ViewCustomerProfileException extends PaymentException {
    public ViewCustomerProfileException(String message){
        super(message);
    }
}
