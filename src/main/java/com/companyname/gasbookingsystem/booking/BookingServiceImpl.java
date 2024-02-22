package com.companyname.gasbookingsystem.booking;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.NewBookingException;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CylinderRepository cylinderRepository;

    @Override
    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }
    @Override
    public Booking getBookingById(Integer id) throws BookingNotFoundException {
        Optional<Booking> booking=this.bookingRepository.findById(id);
        if (booking.isEmpty()) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
        Booking booking1=booking.get();
        return booking1;
    }

    @Override
    public Booking createBooking(BookingDTO bookingDTO) throws NewBookingException, BookingNotFoundException, CustomerNotExistsWithId {
        Optional<Customer> customerIdOptional=this.customerRepository.findById(bookingDTO.getCustomerId());
        if(!customerIdOptional.isPresent()){
            throw new CustomerNotExistsWithId("Id"+bookingDTO.getCustomerId());
        }
        Optional<Cylinder> cylinderIdOptional=this.cylinderRepository.findById(bookingDTO.getCylinderId());
        if(!cylinderIdOptional.isPresent()){
            throw new CustomerNotExistsWithId("Id"+bookingDTO.getCylinderId());
        }
        Customer customerId=customerIdOptional.get();
        Cylinder cylinderId=cylinderIdOptional.get();
        if(!cylinderId.getIsActive()) throw new BookingNotFoundException("Enter an valid cylinder ID");
        cylinderId.setIsActive(Boolean.FALSE);
        this.cylinderRepository.save(cylinderId);
        Booking booking =new Booking();
        booking.setCylinder(cylinderId);
        booking.setStatus(BookingStatusType.PENDING);
        this.bookingRepository.save(booking);
        customerId.getBookingList().add(booking);
         this.customerRepository.save(customerId);
         return this.bookingRepository.save(booking);
    }

    @Override
    public Booking updateBooking(Booking booking) throws BookingNotFoundException {
        if (!bookingRepository.existsById(booking.getId())) {
            throw new BookingNotFoundException("Booking not found with id: " + booking.getId());
        }
        booking.setId(booking.getId());
        return bookingRepository.save(booking);
    }
//    @Override
//    public void deleteBooking(Integer id) throws BookingNotFoundException {
//        if (!bookingRepository.existsById(id)) {
//            throw new BookingNotFoundException("Booking not found with id: " + id);
//        }
//        bookingRepository.deleteById(id);
//
//    }

}
