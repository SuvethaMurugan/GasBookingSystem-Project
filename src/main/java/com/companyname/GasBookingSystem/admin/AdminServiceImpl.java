package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.admin.Exception.AdminException;
import com.companyname.GasBookingSystem.customer.Customer;
import com.companyname.GasBookingSystem.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Admin loginAdminID(Integer adminId, String password) throws AdminException {
        Optional<Admin> adminLogin = adminRepository.findById(adminId);
        if(adminLogin.isEmpty()) {
            throw new AdminException("Login Credentials does not match");
        }
        Admin admin=adminLogin.get();
        if(admin.getPassword().equals(password))
            return admin;
        else throw new AdminException("Login Credentials does not match");
    }
    @Override
    public Admin loginAdminEmail (String email, String password) throws AdminException{
        Optional<Admin> adminEmailLogin = adminRepository.findByEmail(email);
        if(adminEmailLogin.isEmpty()){
            throw new AdminException("Login Credentials does not match");
        }
        Admin admin=adminEmailLogin.get();
        if(admin.getPassword().equals(password))
            return admin;
        else
            throw new AdminException("Login Credentials does not match");
    }

    @Override
    public String adminLogout(String email, String password) {
        return "Logged Out Successfully";
    }
}
