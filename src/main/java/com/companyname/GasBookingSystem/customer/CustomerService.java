package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;

import javax.security.auth.login.AccountException;

public interface CustomerService {
    Customer registerUser(Customer registeruser) throws CustomerException;

    Customer loginUserName(String userName, String password) throws CustomerException;

    Customer updateProfile(UpdateDTO updateAccount);

    Customer loginUserMobileNo(Customer loginMobile) throws CustomerException;

}

