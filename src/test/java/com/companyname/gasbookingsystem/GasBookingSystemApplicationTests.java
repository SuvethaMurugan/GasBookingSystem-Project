package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingService;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerService;
import com.companyname.gasbookingsystem.customer.dto.*;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
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
class GasBookingSystemApplicationTests {
	@Autowired
	private  CustomerService customerService;
	@Autowired
	private PaymentService paymentService;
	@Autowired
	private CylinderService cylinderService;
	@Autowired
	private BookingService bookingService;

	CylinderDTO cylinderDTO;
	Customer  customer;
	RegisterUserDTO registerUserDTO;
    Address address;
	MobileNumLoginDTO mobileNumLoginDTO;
	UserNameLoginDTO userNameLoginDTO;
	UpdateDTO updateDTO;


    @BeforeEach
    public void createCustomer(){
         address=new Address(1,12,"StreetName","Chennai","6000001");
         registerUserDTO=new RegisterUserDTO("Suvetha","Suvetha@123","suvetha@gmail.com","9489696937",address);
		 cylinderDTO=new CylinderDTO(CylinderType.HouseHold,10.0,500.0);
		customer=Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha2003@gmail.com").address(address).build();
    }
	@AfterEach
	public void DeleteValue(){
		customer=null;
		address=null;
		registerUserDTO=null;
		cylinderDTO=null;
	}
	@DisplayName("Payment Creation Test")
	@Test
	 void PaymentCreationTest()
	{

		Cylinder cylinder=null;
		Booking booking=null;
		try {
           customer=customerService.registerUser(customer);
		   Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
		   cylinder=cylinderService.addCylinder(cylinderDTO);
		   BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
		   booking=bookingService.createBooking(bookingDTO);
			PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
		   Assertions.assertThrows(PaymentException.class,()->paymentService.paymentCylinder(paymentUpdateDTO));

		}
		catch (CustomerException | InvalidPasswordException  | BookingNotFoundException| CustomerNotExistsWithId|AddCylinderException |InvalidEmailException e) {
			Assertions.fail(e.getMessage());
		}
    }
	@DisplayName("Payment Creation Equals Test")
	@Test
	void PaymentCreationTestEqualsTest()
	{
		Cylinder cylinder=null;
		Booking booking=null;
		try {
			customer=customerService.registerUser(customer);
			Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
			cylinder=cylinderService.addCylinder(cylinderDTO);
			BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
			booking=bookingService.createBooking(bookingDTO);
			PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
			paymentService.paymentCylinder(paymentUpdateDTO);

		}
		catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException| CustomerNotExistsWithId|AddCylinderException |InvalidEmailException e) {
			System.out.println(e.getMessage());
			Assertions.assertEquals("The Bank should be registered for the payment transaction",e.getMessage());
		}
	}
}
