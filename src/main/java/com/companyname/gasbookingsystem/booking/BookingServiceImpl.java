package com.companyname.gasbookingsystem.booking;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.NewBookingException;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {

    private final BookingRepository bookingRepository;
    private final CustomerRepository customerRepository;
    private final CylinderRepository cylinderRepository;

    public BookingServiceImpl(BookingRepository bookingRepository, CustomerRepository customerRepository, CylinderRepository cylinderRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.cylinderRepository = cylinderRepository;
    }

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
        return booking.get();
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
        if(Boolean.FALSE.equals(cylinderId.getIsActive())) throw new BookingNotFoundException("Enter an valid cylinder ID");
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


}
