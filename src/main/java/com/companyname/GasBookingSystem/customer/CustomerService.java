package com.companyname.GasBookingSystem.customer;


import com.companyname.GasBookingSystem.customer.Exception.InvalidEmailException;
import com.companyname.GasBookingSystem.customer.Exception.InvalidPasswordException;
import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderType;

import java.util.List;
import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;

public interface CustomerService {

    Customer registerUser(Customer registeruser) throws CustomerException, InvalidPasswordException, InvalidEmailException;

    Customer loginUserName(String userName, String password) throws CustomerException;

    Customer updateProfile(UpdateDTO updateAccount) throws CustomerException;

    Customer loginUserMobileNo(Customer loginMobile) throws CustomerException;
    List<Cylinder> getAllCylindersOfMedical(CylinderType type);

}

