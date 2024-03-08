package com.companyname.gasbookingsystem.admin;

import com.companyname.gasbookingsystem.admin.DTO.AdminEmailDto;
import com.companyname.gasbookingsystem.admin.DTO.AdminLoginDTO;
import com.companyname.gasbookingsystem.admin.exception.AdminException;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingRepository;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;


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
    public Admin loginAdminID(AdminLoginDTO adminLoginDto ) throws AdminException {
        Optional<Admin> adminLogin = adminRepository.findById(adminLoginDto.getAdminId());
        if(adminLogin.isEmpty()) {
            throw new AdminException(MESSAGE);
        }
        Admin admin = adminLogin.get();
        if (admin.getPassword().equals(adminLoginDto.getPassword()))
            return admin;
        else throw new AdminException(MESSAGE);
    }


    @Override
    public Admin loginAdminEmail(AdminEmailDto adminEmailDto) throws AdminException {
        Optional<Admin> adminEmailLogin = adminRepository.findByEmail(adminEmailDto.getAdminEmail());
        if(adminEmailLogin.isEmpty()){
            throw new AdminException(MESSAGE);
        }
        Admin admin = adminEmailLogin.get();
        if (admin.getPassword().equals(adminEmailDto.getPassword()))
            return admin;
        else
            throw new AdminException(MESSAGE);
    }


    @Override
    public String adminLogout(AdminEmailDto adminEmailDto) {
        return "Logged Out Successfully";
    }

    @Override
    public List<Booking> getAllListOfCylinders() {
        return this.bookingRepository.findAllByDeliveryDate(LocalDate.now());
    }
}
