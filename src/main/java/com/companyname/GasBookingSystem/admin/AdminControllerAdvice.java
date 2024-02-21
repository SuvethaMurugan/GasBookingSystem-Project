package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.admin.Exception.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AdminControllerAdvice {
    @ExceptionHandler(value = {AdminException.class})
    public ResponseEntity<String> AdminException(AdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
