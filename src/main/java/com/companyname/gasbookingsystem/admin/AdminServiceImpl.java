package com.companyname.gasbookingsystem.admin;

import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingRepository;

import java.time.LocalDate;


@Service
public class AdminServiceImpl implements AdminService{
    private final CustomerRepository customerRepository;
    private final AdminRepository adminRepository;
    private final BookingRepository bookingRepository;

    public AdminServiceImpl(CustomerRepository customerRepository, AdminRepository adminRepository, BookingRepository bookingRepository) {
        this.customerRepository = customerRepository;
        this.adminRepository = adminRepository;
        this.bookingRepository = bookingRepository;
    }
    private static final String MESSAGE ="Login Credentials does not match";

    @Override
    public List<Customer> getAllCustomers() {
        return this.customerRepository.findAll();
    }

    @Override
    public Admin loginAdminID(Integer adminId, String password) throws AdminException {
        Optional<Admin> adminLogin = adminRepository.findById(adminId);
        if(adminLogin.isEmpty()) {
            throw new AdminException(MESSAGE);
        }
        Admin admin=adminLogin.get();
        if(admin.getPassword().equals(password))
            return admin;
        else throw new AdminException(MESSAGE);
    }
    @Override
    public Admin loginAdminEmail (String email, String password) throws AdminException{
        Optional<Admin> adminEmailLogin = adminRepository.findByEmail(email);
        if(adminEmailLogin.isEmpty()){
            throw new AdminException(MESSAGE);
        }
        Admin admin=adminEmailLogin.get();
        if(admin.getPassword().equals(password))
            return admin;
        else
            throw new AdminException(MESSAGE);
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
