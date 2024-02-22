package com.companyname.gasbookingsystem.admin;


import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.customer.Customer;


import com.companyname.gasbookingsystem.booking.Booking;

import java.util.List;

public interface AdminService {


    List<Customer> getAllCustomers();
    Admin loginAdminID (Integer adminId, String password)throws AdminException;
    Admin loginAdminEmail (String email,String password)throws AdminException;
    String adminLogout(String email,String password);
    List<Booking> getAllListOfCylinders();

}
