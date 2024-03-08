package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.bank.Bank;
import com.companyname.gasbookingsystem.bank.BankService;
import com.companyname.gasbookingsystem.bank.dto.CreateBankDTO;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingService;
import com.companyname.gasbookingsystem.booking.BookingStatusType;
import com.companyname.gasbookingsystem.booking.DTO.BookingDTO;
import com.companyname.gasbookingsystem.booking.exception.BookingNotFoundException;
import com.companyname.gasbookingsystem.booking.exception.CustomerNotExistsWithId;
import com.companyname.gasbookingsystem.booking.exception.CylinderNotExistsWithId;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerService;
import com.companyname.gasbookingsystem.customer.dto.RegisterUserDTO;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.customer.exception.ViewCustomerProfileException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderService;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.payment.PaymentService;
import com.companyname.gasbookingsystem.payment.PaymentStatusType;
import com.companyname.gasbookingsystem.payment.dto.BankLinkingDTO;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
import com.companyname.gasbookingsystem.payment.exception.BankUpdateException;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class PaymentModuleTest {
    @Autowired
    private  CustomerService customerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private CylinderService cylinderService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private BankService bankService;

    CylinderDTO cylinderDTO;
    Customer  customer;
    RegisterUserDTO registerUserDTO;
    Address address;
    CreateBankDTO createBankDTO;
    CreateBankDTO createBankDTO1;
    @BeforeEach
    public void createCustomer(){
        address=new Address(1,12,"StreetName","Chennai","6000001");
        registerUserDTO=new RegisterUserDTO("Suvetha","Suvetha@123","suvetha@gmail.com","9489696937",address);
        cylinderDTO=new CylinderDTO(CylinderType.HouseHold,10.0,500.0);
        customer=Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha2003@gmail.com").address(address).build();
        createBankDTO=new CreateBankDTO("suvetha","Suvetha@123",1000.0);
        createBankDTO1=new CreateBankDTO("suvetha","Suvetha@123",300.0);
    }
    @AfterEach
    public void DeleteValue(){
        customer=null;
        address=null;
        registerUserDTO=null;
        cylinderDTO=null;
    }
    @DisplayName("PaymentCreationWithoutBankAccountTest")
    @Test
    void PaymentCreationWithoutBankAccountTest()
    {

        Cylinder cylinder;
        Booking booking;
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
        } catch (CylinderNotExistsWithId e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("PaymentCreationWithoutBankAccountEqualsTest")
    @Test
    void PaymentCreationWithoutBankAccountEqualsTest()
    {
        Cylinder cylinder;
        Booking booking;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            paymentService.paymentCylinder(paymentUpdateDTO);

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               CustomerNotExistsWithId | AddCylinderException | InvalidEmailException | CylinderNotExistsWithId e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("The Bank should be registered for the payment transaction",e.getMessage());
        }
    }
    @DisplayName("PaymentWithNotLinkingBankAccountTest")
    @Test
    void PaymentWithNotLinkingBankAccountTest()
    {
        Cylinder cylinder;
        Booking booking;

        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            Assertions.assertNotNull(bankService.createAccount(createBankDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertNotNull(paymentService.paymentCylinder(paymentUpdateDTO));

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException| CustomerNotExistsWithId|AddCylinderException |InvalidEmailException e) {
            System.out.println(e.getMessage());
            Assertions.assertEquals("The Bank should be registered for the payment transaction",e.getMessage());
        } catch (CylinderNotExistsWithId e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertNotNull(paymentService.paymentCylinder(paymentUpdateDTO));

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            System.out.println(e.getMessage());
           Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithBankAccountTwiceTest")
    @Test
    void PaymentWithBankAccountTwiceTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertNotNull(paymentService.paymentCylinder(paymentUpdateDTO));
            Assertions.assertThrows(PaymentException.class,()->paymentService.paymentCylinder(paymentUpdateDTO));

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            System.out.println(e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithBankAccountTwiceWithAssertEqualsTest")
    @Test
    void PaymentWithBankAccountTwiceWithAssertEqualsTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertNotNull(paymentService.paymentCylinder(paymentUpdateDTO));
            paymentService.paymentCylinder(paymentUpdateDTO);

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            Assertions.assertEquals("The payment for this booking is paid", e.getMessage());
        }
    }
    @DisplayName("PaymentWithBankAccountTwiceWithAssertEqualsTest")
    @Test
    void PaymentWithBankAccountTwiceWithInsufficientBalanceTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO1);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertThrows(PaymentException.class,()->paymentService.paymentCylinder(paymentUpdateDTO));


        }
        catch (CustomerException | InvalidPasswordException | BookingNotFoundException | BankUpdateException |
               CustomerNotExistsWithId | AddCylinderException | InvalidEmailException | CylinderNotExistsWithId e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithBankAccountTwiceWithAssertEqualsTest")
    @Test
    void PaymentWithBankAccountTwiceWithInsufficientBalanceEqualsTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO1);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            paymentService.paymentCylinder(paymentUpdateDTO);


        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            Assertions.assertEquals("The account balance is insufficient", e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountEqualsParameterTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
           Booking resbooking=paymentService.paymentCylinder(paymentUpdateDTO);
            Assertions.assertEquals(BookingStatusType.BOOKED, resbooking.getStatus());
            Assertions.assertEquals(PaymentStatusType.PAID, resbooking.getPayment().getPaymentStatus());
        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("ViewProfileTest")
    @Test
    void ViewProfileTest()
    {

        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            Assertions.assertNotNull(paymentService.viewProfile(customer.getId()));
        }
        catch (CustomerException | InvalidPasswordException  | ViewCustomerProfileException|InvalidEmailException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("ViewProfileWithInvalidIdTest")
    @Test
    void ViewProfileWithInvalidIdTest()
    {

        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            paymentService.viewProfile(2000);
        }
        catch (CustomerException | InvalidPasswordException  | ViewCustomerProfileException|InvalidEmailException e) {
            Assertions.assertEquals("The Entered Id doesn't exists! Enter an valid Id",e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountViewTransactionTest()
    {

        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            Assertions.assertThrows(PaymentException.class,()->paymentService.getTransactions(customer.getId()));
        }
        catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountViewTransactionAssertEqualsTest()
    {
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            paymentService.getTransactions(customer.getId());
        }
        catch (CustomerException | InvalidPasswordException |PaymentException |InvalidEmailException e) {
            Assertions.assertEquals("No bookings were made",e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithViewTransactionTest()
    {
        Cylinder cylinder;
        Booking booking;

        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Booking resbooking=paymentService.paymentCylinder(paymentUpdateDTO);
            Assertions.assertEquals(LocalDate.now(), resbooking.getPayment().getPaymentDate());
            Assertions.assertEquals("Online Mode",resbooking.getPayment().getPaymentType());
            Assertions.assertEquals(cylinder.getPrice(),resbooking.getPayment().getPaymentAmount());
            Assertions.assertEquals(BookingStatusType.BOOKED, resbooking.getStatus());
            Assertions.assertEquals(PaymentStatusType.PAID, resbooking.getPayment().getPaymentStatus());
            Assertions.assertNotNull(paymentService.getTransactions(customer.getId()));
        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException| BankUpdateException |CustomerNotExistsWithId|AddCylinderException |InvalidEmailException e) {
            Assertions.fail(e.getMessage());
        } catch (CylinderNotExistsWithId e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountWithInvalidIDTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(900, bank.getBankId(), bank.getPassword());
            Assertions.assertThrows(BankUpdateException.class,()->paymentService.bankLinkingAccount(bankLinkingDTO));

        }
        catch (CustomerException | InvalidPasswordException | CustomerNotExistsWithId | AddCylinderException |
               InvalidEmailException | BookingNotFoundException | CylinderNotExistsWithId e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountWithInvalidPasswordTest()
    {
        Cylinder cylinder;
        Booking booking;

        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(),"S435Turfsf");
            Assertions.assertThrows(BankUpdateException.class,()->paymentService.bankLinkingAccount(bankLinkingDTO));

        }
        catch (CustomerException | InvalidPasswordException | CustomerNotExistsWithId | AddCylinderException |
               InvalidEmailException | BookingNotFoundException | CylinderNotExistsWithId e) {
            Assertions.fail(e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountWithInvalidIDEqualsTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(900, bank.getBankId(), bank.getPassword());
            paymentService.bankLinkingAccount(bankLinkingDTO);

        }
        catch (CustomerException | InvalidPasswordException | BankUpdateException | CustomerNotExistsWithId |
               AddCylinderException | InvalidEmailException | BookingNotFoundException | CylinderNotExistsWithId e) {
            Assertions.assertEquals("Customer doesn't exist",e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentWithLinkingBankAccountWithInvalidPasswordEqualsTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(),"S435Turfsf");
           paymentService.bankLinkingAccount(bankLinkingDTO);

        }
        catch (CustomerException | InvalidPasswordException | BankUpdateException | CustomerNotExistsWithId |
               AddCylinderException | InvalidEmailException | BookingNotFoundException | CylinderNotExistsWithId e) {
            Assertions.assertEquals("Entered User ID and Password doesn't exist",e.getMessage());
        }
    }
    @DisplayName("PaymentWithLinkingBankAccountTest")
    @Test
    void PaymentCylinderCheckTest()
    {
        Cylinder cylinder;
        Booking booking;
        Bank bank;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            bank=bankService.createAccount(createBankDTO);
            BankLinkingDTO bankLinkingDTO=new BankLinkingDTO(customer.getId(), bank.getBankId(), bank.getPassword());
            Assertions.assertNotNull(paymentService.bankLinkingAccount(bankLinkingDTO));
            PaymentUpdateDTO paymentUpdateDTO=new PaymentUpdateDTO(customer.getId(), booking.getId());
            Assertions.assertNotNull(paymentService.paymentCylinder(paymentUpdateDTO));

        }
        catch (CustomerException | InvalidPasswordException | PaymentException | BookingNotFoundException |
               BankUpdateException | CustomerNotExistsWithId | AddCylinderException | InvalidEmailException |
               CylinderNotExistsWithId e) {
            System.out.println(e.getMessage());
            Assertions.fail(e.getMessage());
        }
    }


}
