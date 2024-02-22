package com.companyname.gasbookingsystem.admin;

import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingRepository;

import java.time.LocalDate;


@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private BookingRepository bookingRepository;
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
    @Override
    public List<Booking> getAllListOfCylinders() {
        return this.bookingRepository.findAllByDeliveryDate(LocalDate.now());

    }
}