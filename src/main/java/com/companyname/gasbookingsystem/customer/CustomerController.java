package com.companyname.gasbookingsystem.customer;


import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.dto.UpdateDTO;
import com.companyname.gasbookingsystem.customer.dto.MobileNumLoginDTO;
import com.companyname.gasbookingsystem.customer.dto.RegisterUserDTO;
import com.companyname.gasbookingsystem.customer.dto.UserNameLoginDTO;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.login.AccountException;


@RestController
public class CustomerController {
    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/availability/{type}")
    public List<Cylinder> getAllCylinders(@PathVariable("type") CylinderType cylindertype) {
        return this.customerService.getAllCylindersOfSpecifiedType(cylindertype);
    }


    @PostMapping("/register")
    public Customer registerUser(@RequestBody RegisterUserDTO newUser) throws CustomerException, InvalidPasswordException, InvalidEmailException {
        return this.customerService.registerUser(Customer.builder().userName(newUser.getUserName()).password(newUser.getPassword()).mobileNo(newUser.getMobileNo()).email(newUser.getEmail()).address(newUser.getAddress()).build());
    }

    @PostMapping("/login/mobilenum")
    public Customer loginUserMobileNo(@RequestBody MobileNumLoginDTO loginMobile) throws CustomerException, AccountException {
        return this.customerService.loginUserMobileNo(loginMobile.getMobileNo(), loginMobile.getPassword());
    }

    @PostMapping("/login/userName")
    public Customer loginUserName(@RequestBody UserNameLoginDTO nameLoginDTO) throws CustomerException, AccountException {
        return this.customerService.loginUserName(nameLoginDTO.getUserName(), nameLoginDTO.getPassword());
    }

    @PatchMapping("/updateProfile")
    public Customer updateProfile(@RequestBody UpdateDTO updateAccount) throws CustomerException {
        return this.customerService.updateProfile(updateAccount);
    }
}

