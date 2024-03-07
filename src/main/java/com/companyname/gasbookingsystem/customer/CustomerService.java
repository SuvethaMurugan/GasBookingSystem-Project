package com.companyname.gasbookingsystem.customer;


import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderType;

import java.util.List;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.dto.UpdateDTO;

public interface CustomerService {

    Customer registerUser(Customer registeruser) throws CustomerException, InvalidPasswordException, InvalidEmailException;

    Customer loginUserName(String userName, String password) throws CustomerException;

    Customer updateProfile(UpdateDTO updateAccount) throws CustomerException;
    Customer loginUserMobileNo(String mobileNo, String password) throws CustomerException;
    List<Cylinder> getAllCylindersOfSpecifiedType(CylinderType type);

}

