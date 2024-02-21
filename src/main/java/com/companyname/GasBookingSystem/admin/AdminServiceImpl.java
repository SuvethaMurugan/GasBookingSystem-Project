package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.booking.Booking;
import com.companyname.GasBookingSystem.booking.BookingRepository;
import com.companyname.GasBookingSystem.customer.CustomerRepository;
import com.companyname.GasBookingSystem.customer.CustomerService;
import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class AdminServiceImpl implements AdminService{
    @Autowired
    private BookingRepository bookingRepository;
    @Override
    public List<Booking> getAllListOfCylinders() {
        return this.bookingRepository.findAllByDeliveryDate(LocalDate.now());
    }
}
