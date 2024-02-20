package com.companyname.GasBookingSystem.booking;

import com.companyname.GasBookingSystem.booking.exception.NewBookingException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BookingControllerAdvice {

    @ExceptionHandler(NewBookingException.class)
    public ResponseEntity<String> handleBookingException(NewBookingException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Add other exception handlers as needed
}
