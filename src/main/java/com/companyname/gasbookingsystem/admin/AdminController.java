package com.companyname.gasbookingsystem.admin;


import com.companyname.gasbookingsystem.admin.DTO.AdminLoginDTO;
import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.customer.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/customers")
    public List<Customer> customerList() {
        return this.adminService.getAllCustomers();
    }

    @PostMapping("/login/adminId")
    public Admin loginAdmin(@RequestBody AdminLoginDTO adminlogin) throws AdminException {
        return this.adminService.loginAdminID(adminlogin.getAdminId(), adminlogin.getPassword());
    }

    @PostMapping("/login/emailId")
    public Admin loginAdminEmail(@RequestBody AdminLoginDTO adminEmail) throws AdminException {
        return this.adminService.loginAdminEmail(adminEmail.getEmailId(), adminEmail.getPassword());
    }

    @PostMapping("/logout")
    public String adminLogout(@RequestBody AdminLoginDTO logout) {
        return this.adminService.adminLogout(logout.getEmailId(), logout.getPassword());
    }

    @GetMapping("/status")
    public List<Booking> getAllBookings() {
        return this.adminService.getAllListOfCylinders();

    }
}
