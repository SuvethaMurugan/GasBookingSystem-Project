package com.companyname.gasbookingsystem.admin;

import com.companyname.gasbookingsystem.admin.exception.AdminException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class AdminControllerAdvice {
    @ExceptionHandler(value = {AdminException.class})
    public ResponseEntity<String> adminException(AdminException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
}
