package com.companyname.GasBookingSystem.booking;

import com.companyname.GasBookingSystem.booking.DTO.BookingDTO;
import com.companyname.GasBookingSystem.booking.exception.BookingNotFoundException;
import com.companyname.GasBookingSystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.GasBookingSystem.booking.exception.NewBookingException;
import com.companyname.GasBookingSystem.customer.Customer;
import com.companyname.GasBookingSystem.customer.CustomerRepository;
import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class BookingServiceImpl implements BookingService {
    private final BookingRepository bookingRepository;
    @Autowired
    public BookingServiceImpl(BookingRepository bookingRepository) {

        this.bookingRepository = bookingRepository;
    }
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CylinderRepository cylinderRepository;
    //@Autowired
    private Booking booking;
    @Override
    public List<Booking> getAllBookings() {

        return bookingRepository.findAll();
    }
    @Override
    public Booking getBookingById(Integer id) throws BookingNotFoundException {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }

        return bookingRepository.getById(id);
    }

    //@Override
    /*public Booking createBooking(Booking newbooking) {
        Optional<Booking> bookingOpt = this.bookingRepository.findById(newbooking.getId());
        if(bookingOpt.isPresent())
            throw new NewBookingException("BookingId is already registered"+newbooking.getId());
         Booking booking = new Booking();
         booking.setBookingDate(newbooking.getBookingDate());
         booking.setDeliveryDate(newbooking.getDeliveryDate());
         booking.setCustomer(newbooking.getCustomer());
         booking.setCylinder(newbooking.getCylinder());
         booking.setId(newbooking.getId());
        return bookingRepository.save(booking);

    }*/

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
        //if(!customerId.)
        if(!cylinderId.getIsActive()) throw new BookingNotFoundException("Enter an valid cylinder ID");
        cylinderId.setIsActive(Boolean.FALSE);
        this.cylinderRepository.save(cylinderId);
        Booking booking =new Booking();
        booking.setCylinder(cylinderId);
        //LocalDate bookDate= LocalDate.now();
        //LocalDate deiveryDate=bookDate.plusDays(3);
        //booking.setBookingDate(bookDate);
        //booking.setDeliveryDate(deiveryDate);
        booking.setStatus(BookingStatusType.valueOf("Pending"));


        customerId.getBookingList().add(booking);
         this.customerRepository.save(customerId);
        //Optional<Booking> bookingOpt = this.bookingRepository.findById(booking.getId());
        //if(bookingOpt.isPresent())
          //  throw new NewBookingException("BookingId is already registered"+booking.getId());
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
    @Override
    public void deleteBooking(Integer id) throws BookingNotFoundException {
        if (!bookingRepository.existsById(id)) {
            throw new BookingNotFoundException("Booking not found with id: " + id);
        }
        bookingRepository.deleteById(id);

    }
}
