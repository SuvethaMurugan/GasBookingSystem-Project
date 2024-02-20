package com.companyname.GasBookingSystem.customer;


import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;
import com.companyname.GasBookingSystem.customer.dto.mobileNumLoginDTO;
import com.companyname.GasBookingSystem.customer.dto.registerUserDTO;
import com.companyname.GasBookingSystem.customer.dto.userNameLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;


@RestController
public class CustomerController {
    @Autowired

    private CustomerService customerService;

    @GetMapping("/availability/{type}")
    public List<Cylinder> getAllCylinders(@PathVariable("type") CylinderType cylindertype) {
        return this.customerService.getAllCylindersOfMedical(cylindertype);
    }

    @PostMapping("/register")
    public Customer registerUser(@RequestBody registerUserDTO newUser) throws CustomerException {
        return this.customerService.registerUser(Customer.builder().userName(newUser.getUserName()).password(newUser.getPassword()).mobileNo(newUser.getMobileNo()).email(newUser.getEmail()).build());
    }

    @PostMapping("/login/mobilenum")
    public Customer loginUserMobileNo(@RequestBody mobileNumLoginDTO loginMobile) throws CustomerException, AccountException {
        return this.customerService.loginUserMobileNo(Customer.builder().mobileNo(loginMobile.getMobileNo()).password(loginMobile.getPassword()).build());
    }

    @PostMapping("/login/userName")
    public Customer loginUserName(@RequestBody userNameLoginDTO nameLoginDTO) throws CustomerException, AccountException {
        return this.customerService.loginUserName(nameLoginDTO.getUserName(), nameLoginDTO.getPassword());
    }

    @PatchMapping("/updateProfile")
    public Customer updateProfile(@RequestBody UpdateDTO updateAccount) {
        return this.customerService.updateProfile(updateAccount);
    }
}

