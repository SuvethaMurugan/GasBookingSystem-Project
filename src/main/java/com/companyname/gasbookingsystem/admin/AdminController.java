package com.companyname.gasbookingsystem.admin;


import com.companyname.gasbookingsystem.admin.DTO.AdminEmailDto;
import com.companyname.gasbookingsystem.admin.DTO.AdminLoginDTO;
import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.customer.Customer;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping("/customers")
    public List<Customer> customerList() {
        return this.adminService.getAllCustomers();
    }

    @PostMapping("/login/adminId")
    public Admin loginAdminID(@RequestBody AdminLoginDTO adminlogin) throws AdminException {
        return this.adminService.loginAdminID(adminlogin);
    }

    @PostMapping("/login/emailId")
    public Admin loginAdminEmail(@RequestBody AdminEmailDto adminEmail) throws AdminException {
        return this.adminService.loginAdminEmail(adminEmail);
    }

    @PostMapping("/logout")
    public String adminLogout(@RequestBody AdminEmailDto logout) {
        return this.adminService.adminLogout(logout);
    }

    @GetMapping("/status")
    public List<Booking> getAllBookings() {
        return this.adminService.getAllListOfCylinders();

    }
}
