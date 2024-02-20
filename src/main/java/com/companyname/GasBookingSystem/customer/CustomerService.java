package com.companyname.GasBookingSystem.customer;


import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;

import java.util.List;
import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;
import jakarta.validation.ConstraintViolationException;

import javax.security.auth.login.AccountException;

public interface CustomerService {
     List<Cylinder> getAllCylindersOfMedical(CylinderType type);
    Customer registerUser(Customer registeruser) throws CustomerException;

    Customer loginUserName(String userName, String password) throws CustomerException;

    Customer updateProfile(UpdateDTO updateAccount) ;

    Customer loginUserMobileNo(Customer loginMobile) throws CustomerException;

}

