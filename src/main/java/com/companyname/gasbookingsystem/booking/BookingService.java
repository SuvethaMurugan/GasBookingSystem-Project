package com.companyname.gasbookingsystem.booking;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;

import java.util.List;
public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(Integer id) throws BookingNotFoundException;

    Booking createBooking(BookingDTO bookingDTO) throws BookingNotFoundException, CustomerNotExistsWithId, CylinderNotExistsWithId;

    Booking updateBooking(Booking booking) throws BookingNotFoundException;


}


