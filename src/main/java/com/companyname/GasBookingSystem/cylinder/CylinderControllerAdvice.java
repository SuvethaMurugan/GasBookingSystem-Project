package com.companyname.GasBookingSystem.cylinder;

import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.deleteCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.getCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.updateCylinderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CylinderControllerAdvice {
    @ExceptionHandler(value={AddCylinderException.class})
    public ResponseEntity<String> AddCylinderException(AddCylinderException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={getCylinderException.class})
    public ResponseEntity<String> getCylinderException(getCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={updateCylinderException.class})
    public ResponseEntity<String> UpdateCylinderException(updateCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={deleteCylinderException.class})
    public ResponseEntity<String> deleteCylinderException(deleteCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }




}
