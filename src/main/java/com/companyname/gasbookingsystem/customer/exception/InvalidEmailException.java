package com.companyname.gasbookingsystem.customer.exception;

public class InvalidEmailException extends Throwable{
    public InvalidEmailException(String message){
        super(message);
    }
}
