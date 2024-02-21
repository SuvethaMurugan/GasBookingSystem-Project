package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.admin.DTO.AdminLoginDTO;
import com.companyname.GasBookingSystem.admin.Exception.AdminException;
import com.companyname.GasBookingSystem.customer.Customer;
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
    public String adminLogout(@RequestBody AdminLoginDTO logout){
        return this.adminService.adminLogout(logout.getEmailId(),logout.getPassword());
    }
}
