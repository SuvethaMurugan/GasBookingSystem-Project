package com.companyname.gasbookingsystem.booking;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.DTO.CustomerBookedDTO;
import com.companyname.gasbookingsystem.booking.DTO.RefillCylinderDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;

import java.util.List;
public interface BookingService {

    List<Booking> getAllBookings();

    Booking getBookingById(Integer id) throws BookingNotFoundException;

    Booking createBooking(BookingDTO bookingDTO) throws BookingNotFoundException, CustomerNotExistsWithId, CylinderNotExistsWithId;

    Booking updateBooking(Booking booking) throws BookingNotFoundException;


    List<CustomerBookedDTO> getBookingByCustomerID(Integer id);

    Cylinder refillBooking(RefillCylinderDTO refillCylinderDTO) throws CylinderNotExistsWithId, BookingNotFoundException, PaymentException, CustomerNotExistsWithId;
}


