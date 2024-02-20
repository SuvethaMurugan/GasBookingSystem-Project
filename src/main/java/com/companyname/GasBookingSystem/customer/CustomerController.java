package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CustomerController {
    @Autowired
    private CustomerService customerService;
    @GetMapping("/availability/{type}")
    public List<Cylinder> getAllCylinders(@PathVariable("type") CylinderType cylindertype){
        return this.customerService.getAllCylindersOfMedical(cylindertype);
    }
}
