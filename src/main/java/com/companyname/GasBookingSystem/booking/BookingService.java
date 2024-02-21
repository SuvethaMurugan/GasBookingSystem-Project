package com.companyname.GasBookingSystem.booking;
import com.companyname.GasBookingSystem.booking.DTO.BookingDTO;
import com.companyname.GasBookingSystem.booking.exception.BookingNotFoundException;
import com.companyname.GasBookingSystem.booking.exception.CustomerNotExistsWithId;
import org.springframework.http.ResponseEntity;

import java.util.List;
public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(Integer id) throws BookingNotFoundException;

    Booking createBooking(BookingDTO bookingDTO) throws BookingNotFoundException, CustomerNotExistsWithId;

    Booking updateBooking(Booking booking) throws BookingNotFoundException;

   // void deleteBooking(Integer id) throws BookingNotFoundException;
}


