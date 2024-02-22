package com.companyname.gasbookingsystem.booking;

import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.NewBookingException;
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
    @ExceptionHandler(BookingNotFoundException.class)
    public ResponseEntity<String> handleBookingNotFoundException(BookingNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(CustomerNotExistsWithId.class)
    public ResponseEntity<String> handleCustomerException(CustomerNotExistsWithId ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    // Add other exception handlers as needed
}
