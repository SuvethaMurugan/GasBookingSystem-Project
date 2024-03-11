//
//package com.companyname.gasbookingsystem;
//
//import com.companyname.gasbookingsystem.address.Address;
//import com.companyname.gasbookingsystem.booking.*;
//import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
//import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
//import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
//import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
//import com.companyname.gasbookingsystem.customer.Customer;
//import com.companyname.gasbookingsystem.customer.CustomerRepository;
//import com.companyname.gasbookingsystem.customer.CustomerService;
//import com.companyname.gasbookingsystem.customer.dto.RegisterUserDTO;
//import com.companyname.gasbookingsystem.customer.exception.CustomerException;
//import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
//import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
//import com.companyname.gasbookingsystem.cylinder.Cylinder;
//import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
//import com.companyname.gasbookingsystem.cylinder.CylinderService;
//import com.companyname.gasbookingsystem.cylinder.CylinderType;
//import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
//import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
//import com.companyname.gasbookingsystem.payment.PaymentService;
//import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
//import com.companyname.gasbookingsystem.payment.exception.PaymentException;
//import com.fasterxml.jackson.databind.annotation.JsonAppend;
//import org.junit.jupiter.api.*;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.annotation.DirtiesContext;
//@SpringBootTest
//@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
//class BookingTest {
//    @Autowired
//    private CustomerService customerService;
//    @Autowired
//    private PaymentService paymentService;
//    @Autowired
//    private CylinderService cylinderService;
//    @Autowired
//    private BookingService bookingService;
//    @Autowired
//    private BookingRepository bookingRepository;
//    @Autowired
//    private CustomerRepository customerRepository;
//    @Autowired
//    private CylinderRepository cylinderRepository;
//
//    CylinderDTO cylinderDTO;
//    Customer customer;
//    RegisterUserDTO registerUserDTO;
//    Address address;
//
//    @BeforeEach
//    public void createCustomer() {
//        address = new Address(1, 12, "StreetName", "Chennai", "6000001");
//        registerUserDTO = new RegisterUserDTO("Suvetha", "Suvetha@123", "suvetha@gmail.com", "9489696937", address);
//        cylinderDTO = new CylinderDTO(CylinderType.HouseHold, 10.0, 500.0);
//        customer = Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha2003@gmail.com").address(address).build();
//    }
//
//    @AfterEach
//    public void DeleteValue() {
//        customer = null;
//        address = null;
//        registerUserDTO = null;
//        cylinderDTO = null;
//    }
//
//    @DisplayName("Booking Creation Test")
//    @Test
//    void BookingCreationTest() {
//
//        Cylinder cylinder = null;
//        Booking booking = null;
//
//
//        try {
//            customer = customerService.registerUser(customer);
//        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
//        } catch (CustomerException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            cylinder = cylinderService.addCylinder(cylinderDTO);
//        } catch (AddCylinderException e) {
//            throw new RuntimeException(e);
//        }
//        BookingDTO bookingDTO = new BookingDTO(customer.getId(), cylinder.getCylinderId());
//        try {
//            try {
//                booking = bookingService.createBooking(bookingDTO);
//            } catch (CylinderNotExistsWithId e) {
//                throw new RuntimeException(e);
//            }
//        } catch (BookingNotFoundException | CustomerNotExistsWithId e) {
//            throw new RuntimeException(e);
//        }
//
//        Assertions.assertNotNull(booking);
//    }
//
//
//    @Test
//    void CylinderIdNotFoundTest() {
//        customer = new Customer();
//        customerRepository.save(customer);
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setCustomerId(customer.getId());
//        bookingDTO.setCylinderId(99);
//
//        Assertions.assertThrows(CylinderNotExistsWithId.class, () -> {
//            bookingService.createBooking(bookingDTO);
//        });
//    }
//
//
//    @Test
//    public void CustomerIdNotFoundTest() {
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setCustomerId(1);
//        bookingDTO.setCylinderId(99);
//        Assertions.assertThrows(CustomerNotExistsWithId.class, () -> {
//            bookingService.createBooking(bookingDTO);
//        });
//    }
//
//    @DisplayName("Customer Registration Test")
//    @Test
//    void CustomerIdNotFoundAssertEqualsTest() {
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setCustomerId(1);
//        bookingDTO.setCylinderId(99);
//        try {
//            bookingService.createBooking(bookingDTO);
//        } catch (BookingNotFoundException | CustomerNotExistsWithId | CylinderNotExistsWithId e) {
//            Assertions.assertEquals("Customer does not exist with id", e.getMessage());
//        }
//
//    }
//
//    @DisplayName("Customer Registration Test")
//    @Test
//    void CylinderIdNotFoundAssertEqualsTest() {
//        customer = new Customer();
//        customerRepository.save(customer);
//        BookingDTO bookingDTO = new BookingDTO();
//        bookingDTO.setCustomerId(customer.getId());
//        bookingDTO.setCylinderId(99);
//
//        try {
//            bookingService.createBooking(bookingDTO);
//        } catch (BookingNotFoundException | CylinderNotExistsWithId | CustomerNotExistsWithId e) {
//            Assertions.assertEquals("Cylinder does not exist with id", e.getMessage());
//        }
//
//    }
//
//    @Test
//    void BookingNotFoundTest() {
//        customer = new Customer();
//        customerRepository.save(customer);
//        Booking booking = new Booking();
//        BookingDTO bookingDTO = new BookingDTO();
//
//        bookingDTO.setCylinderId(1);
//        bookingDTO.setCustomerId(1);
//        booking.setId(1);
//
//
//        Assertions.assertThrows(BookingNotFoundException.class, () -> {
//            bookingService.updateBooking(booking);
//        });
//
//    }
//
//    @Test
//    void updateBookingTest() {
//
//        Cylinder cylinder = null;
//        Booking booking = null;
//
//
//        try {
//            customer = customerService.registerUser(customer);
//        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
//        } catch (CustomerException e) {
//            throw new RuntimeException(e);
//        }
//        try {
//            cylinder = cylinderService.addCylinder(cylinderDTO);
//        } catch (AddCylinderException e) {
//            throw new RuntimeException(e);
//        }
//        BookingDTO bookingDTO = new BookingDTO(customer.getId(), cylinder.getCylinderId());
//        try {
//            booking = bookingService.createBooking(bookingDTO);
//        } catch (BookingNotFoundException | CylinderNotExistsWithId | CustomerNotExistsWithId e) {
//            throw new RuntimeException(e);
//        }
//
//        try {
//            bookingService.updateBooking(booking);
//        } catch (BookingNotFoundException e) {
//            throw new RuntimeException(e);
//        }
//    }
//    @Test
//    void getBookingByIdTest() {
//        customer = new Customer();
//        customerRepository.save(customer);
//        Booking booking = new Booking();
//        BookingDTO bookingDTO = new BookingDTO();
//
//        bookingDTO.setCylinderId(1);
//        bookingDTO.setCustomerId(1);
//        booking.setId(1);
//
//
//        Assertions.assertThrows(BookingNotFoundException.class, () -> {
//            bookingService.getBookingById(booking.getId());
//        });
//
//    }
//    @Test
//    void getAllBookingsTest() throws BookingNotFoundException {
//        customer = new Customer();
//        customerRepository.save(customer);
//        Booking booking = new Booking();
//        BookingDTO bookingDTO = new BookingDTO();
//
//        bookingDTO.setCylinderId(1);
//        bookingDTO.setCustomerId(1);
//        booking.setId(1);
//        booking.setId(2);
//
//
//        bookingService.getAllBookings();
//
//    }
//
//}

package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.booking.*;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerRepository;
import com.companyname.gasbookingsystem.customer.CustomerService;
import com.companyname.gasbookingsystem.customer.dto.RegisterUserDTO;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
import com.companyname.gasbookingsystem.cylinder.CylinderService;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.payment.PaymentService;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import com.fasterxml.jackson.databind.annotation.JsonAppend;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookingTest {
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CylinderService cylinderService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CylinderRepository cylinderRepository;

    CylinderDTO cylinderDTO;
    Customer customer,customerUpdate;
    RegisterUserDTO registerUserDTO;
    Address address;

    @BeforeEach
    public void createCustomer() {
        address = new Address(1, 12, "StreetName", "Chennai", "6000001");
        registerUserDTO = new RegisterUserDTO("Suvetha", "Suvetha@123", "suvetha@gmail.com", "9489696937", address);
        cylinderDTO = new CylinderDTO(CylinderType.HouseHold, 10.0, 500.0);
        customer = Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha2003@gmail.com").address(address).build();
        customerUpdate = Customer.builder().userName("Kaviya").password("Kaviya@123").mobileNo("6789031254").email("kaviya2003@gmail.com").address(address).build();

    }

    @AfterEach
    public void DeleteValue() {
        customer = null;
        address = null;
        registerUserDTO = null;
        cylinderDTO = null;
        customerUpdate=null;
    }

    @DisplayName("Booking Creation Test")
    @Test
    void BookingCreationTest() {

        Cylinder cylinder = null;
        Booking booking = null;


        try {
            customer = customerService.registerUser(customer);
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }
        try {
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        }
        try {
            cylinder = cylinderService.addCylinder(cylinderDTO);
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
        BookingDTO bookingDTO = new BookingDTO(customer.getId(), cylinder.getCylinderId());
        try {
            try {
                booking = bookingService.createBooking(bookingDTO);
            } catch (CylinderNotExistsWithId e) {
                throw new RuntimeException(e);
            }
        } catch (BookingNotFoundException | CustomerNotExistsWithId e) {
            throw new RuntimeException(e);
        }

        Assertions.assertNotNull(booking);
    }


    @Test
    void CylinderIdNotFoundTest() {
        customer = new Customer();
        customerRepository.save(customer);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(customer.getId());
        bookingDTO.setCylinderId(99);

        Assertions.assertThrows(CylinderNotExistsWithId.class, () -> {
            bookingService.createBooking(bookingDTO);
        });
    }


    @Test
     void CustomerIdNotFoundTest() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(1);
        bookingDTO.setCylinderId(99);
        Assertions.assertThrows(CustomerNotExistsWithId.class, () -> {
            bookingService.createBooking(bookingDTO);
        });
    }

    @DisplayName("Customer Registration Test")
    @Test
    void CustomerIdNotFoundAssertEqualsTest() {
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(1);
        bookingDTO.setCylinderId(99);
        try {
            bookingService.createBooking(bookingDTO);
        } catch (BookingNotFoundException | CustomerNotExistsWithId | CylinderNotExistsWithId e) {
            Assertions.assertEquals("Customer does not exist with id", e.getMessage());
        }

    }

    @DisplayName("Customer Registration Test")
    @Test
    void CylinderIdNotFoundAssertEqualsTest() {
        customer = new Customer();
        customerRepository.save(customer);
        BookingDTO bookingDTO = new BookingDTO();
        bookingDTO.setCustomerId(customer.getId());
        bookingDTO.setCylinderId(99);

        try {
            bookingService.createBooking(bookingDTO);
        } catch (BookingNotFoundException | CylinderNotExistsWithId | CustomerNotExistsWithId e) {
            Assertions.assertEquals("Cylinder does not exist with id", e.getMessage());
        }

    }

    @Test
    void BookingNotFoundTest() {
        customer = new Customer();
        customerRepository.save(customer);
        Booking booking = new Booking();
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setCylinderId(1);
        bookingDTO.setCustomerId(1);
        booking.setId(1);


        Assertions.assertThrows(BookingNotFoundException.class, () -> {
            bookingService.updateBooking(booking);
        });

    }

    @Test
    void updateBookingTest() {

        Cylinder cylinder = null;
        Booking booking = null;


        try {
            customer = customerService.registerUser(customer);
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }
        try {
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        }
        try {
            cylinder = cylinderService.addCylinder(cylinderDTO);
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
        BookingDTO bookingDTO = new BookingDTO(customer.getId(), cylinder.getCylinderId());
        try {
            booking = bookingService.createBooking(bookingDTO);
        } catch (BookingNotFoundException | CylinderNotExistsWithId | CustomerNotExistsWithId e) {
            throw new RuntimeException(e);
        }

        try {
            bookingService.updateBooking(new Booking(1,));
        } catch (BookingNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void getBookingByIdTest() {
        customer = new Customer();
        customerRepository.save(customer);
        Booking booking = new Booking();
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setCylinderId(1);
        bookingDTO.setCustomerId(1);
        booking.setId(1);


        Assertions.assertThrows(BookingNotFoundException.class, () -> {
            bookingService.getBookingById(booking.getId());
        });

    }
    @Test
    void getAllBookingsTest() throws BookingNotFoundException {
        customer = new Customer();
        customerRepository.save(customer);
        Booking booking = new Booking();
        BookingDTO bookingDTO = new BookingDTO();

        bookingDTO.setCylinderId(1);
        bookingDTO.setCustomerId(1);
        booking.setId(1);
        booking.setId(2);


            bookingService.getAllBookings();



    }

}















