package com.companyname.GasBookingSystem.admin;

import com.companyname.GasBookingSystem.booking.Booking;

import java.time.LocalDate;
import java.util.List;

public interface AdminService {


    List<Booking> getAllListOfCylinders();
}
