package com.companyname.gasbookingsystem.admin;


import com.companyname.gasbookingsystem.admin.DTO.AdminEmailDto;
import com.companyname.gasbookingsystem.admin.DTO.AdminLoginDTO;
import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.customer.Customer;

import java.util.List;

public interface AdminService {


    List<Customer> getAllCustomers();

    Admin loginAdminID(AdminLoginDTO adminLoginDTO) throws AdminException;

   // Admin loginAdminEmail(AdminEmailDto) throws AdminException;

    Admin loginAdminEmail(AdminEmailDto adminEmailDto) throws AdminException;

    String adminLogout(AdminEmailDto adminEmailDto);

    List<Booking> getAllListOfCylinders();

}
