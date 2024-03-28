package com.companyname.gasbookingsystem.booking;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.DTO.CustomerBookedDTO;
import com.companyname.gasbookingsystem.booking.DTO.RefillCylinderDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    public Booking createBooking(BookingDTO bookingDTO) throws BookingNotFoundException, CustomerNotExistsWithId, CylinderNotExistsWithId {
        Optional<Customer> customerIdOptional=this.customerRepository.findById(bookingDTO.getCustomerId());
        if(!customerIdOptional.isPresent()){
            throw new CustomerNotExistsWithId("Customer does not exist with id");
        }
        Optional<Cylinder> cylinderIdOptional=this.cylinderRepository.findById(bookingDTO.getCylinderId());
        if(!cylinderIdOptional.isPresent()){
            throw new CylinderNotExistsWithId("Cylinder does not exist with id");
        }
        Customer customerId=customerIdOptional.get();
        Cylinder cylinderId=cylinderIdOptional.get();
        if(Boolean.FALSE.equals(cylinderId.getIsActive())) throw new BookingNotFoundException("Enter an valid cylinder ID");
        cylinderId.setIsActive(Boolean.FALSE);
        this.cylinderRepository.save(cylinderId);
        Booking booking =new Booking();
        booking.setCylinder(cylinderId);
        booking.setCustomer(customerId);
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

    @Override
    public List<CustomerBookedDTO> getBookingByCustomerID(Integer id) {
        List<Booking> bookingList=new ArrayList<>(bookingRepository.findAll());
        List<Booking> list=bookingList.stream().filter(booking ->booking.getCustomer().getId().equals(id)).toList();
        List<CustomerBookedDTO>customerBookedDTOList=new ArrayList<>();
        for(Booking s:list){
            CustomerBookedDTO customerbookedDTO=new CustomerBookedDTO();
            customerbookedDTO.setBookingDate(s.getBookingDate());
            customerbookedDTO.setId(s.getId());
            customerbookedDTO.setCylinderid(s.getCylinder().getCylinderId());
            customerbookedDTO.setCylinderType(s.getCylinder().getType());
            customerbookedDTO.setStatus(s.getStatus());
            customerBookedDTOList.add(customerbookedDTO);

        }
        return customerBookedDTOList;
    }

    @Override
    public Cylinder refillBooking(RefillCylinderDTO refillCylinderDTO) throws CylinderNotExistsWithId, BookingNotFoundException, PaymentException, CustomerNotExistsWithId {
        Optional<Cylinder> cylinderIdOptional=this.cylinderRepository.findById(refillCylinderDTO.getCylinderId());
        if(!cylinderIdOptional.isPresent()){
            throw new CylinderNotExistsWithId("Cylinder does not exist with id");
        }
        Optional<Customer> customerIdOptional=this.customerRepository.findById(refillCylinderDTO.getCustomerId());
        if(!customerIdOptional.isPresent()){
            throw new CustomerNotExistsWithId("Customer does not exist with id");
        }
        Customer customerId=customerIdOptional.get();
        Optional<Booking> bookingEntityOptional=this.bookingRepository.findById(refillCylinderDTO.getBookingId());
        if(bookingEntityOptional.isEmpty()) throw new PaymentException("The entered Id doesn't exist for the Customer! Enter an valid booking id");
        Booking bookingId=bookingEntityOptional.get();
        bookingId.setStatus(BookingStatusType.SURRENDERED);
        Cylinder cylinderId=cylinderIdOptional.get();
        cylinderId.setIsActive(Boolean.TRUE);
        this.cylinderRepository.save(cylinderId);
        this.bookingRepository.save(bookingId);
        this.customerRepository.save(customerId);
        return cylinderId;



    }

}
