package com.companyname.gasbookingsystem.cylinder;

import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice
public class CylinderControllerAdvice {

    @ExceptionHandler(value={AddCylinderException.class})
    public ResponseEntity<String> addCylinderException(AddCylinderException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={GetCylinderException.class})
    public ResponseEntity<String> getCylinderException(GetCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={UpdateCylinderException.class})
    public ResponseEntity<String> updateCylinderException(UpdateCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(value={DeleteCylinderException.class})
    public ResponseEntity<String> deleteCylinderException(DeleteCylinderException e)
    {
        return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
    }




}
