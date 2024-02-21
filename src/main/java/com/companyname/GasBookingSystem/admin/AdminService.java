package com.companyname.GasBookingSystem.admin;


import com.companyname.GasBookingSystem.admin.Exception.AdminException;
import com.companyname.GasBookingSystem.customer.Customer;


import com.companyname.GasBookingSystem.booking.Booking;

import java.time.LocalDate;

import java.util.List;

public interface AdminService {


    List<Customer> getAllCustomers();
    Admin loginAdminID (Integer adminId, String password)throws AdminException;
    Admin loginAdminEmail (String email,String password)throws AdminException;
    String adminLogout(String email,String password);
    List<Booking> getAllListOfCylinders();

}
