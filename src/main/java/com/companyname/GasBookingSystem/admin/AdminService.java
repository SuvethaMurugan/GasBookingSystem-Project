package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.admin.Exception.AdminException;
import com.companyname.GasBookingSystem.customer.Customer;

import java.util.List;

public interface AdminService {

    List<Customer> getAllCustomers();
    Admin loginAdminID (Integer adminId, String password)throws AdminException;
    Admin loginAdminEmail (String email,String password)throws AdminException;
    String adminLogout(String email,String password);
}
