package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.CustomerService;
import com.companyname.gasbookingsystem.customer.dto.MobileNumLoginDTO;
import com.companyname.gasbookingsystem.customer.dto.RegisterUserDTO;
import com.companyname.gasbookingsystem.customer.dto.UpdateDTO;
import com.companyname.gasbookingsystem.customer.dto.UserNameLoginDTO;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerTest {

        @Autowired
        private CustomerService customerService;
        Customer  customer;

        Address address;
        MobileNumLoginDTO mobileNumLoginDTO;
        UserNameLoginDTO userNameLoginDTO;
        UpdateDTO updateDTO;
        RegisterUserDTO registerUserDTO;
        CylinderType cylinderType;


        @BeforeEach
        public void createCustomer(){
            address=new Address(1,12,"StreetName","Chennai","6000001");
            registerUserDTO=new RegisterUserDTO("Suvetha","Suvetha@123","suvetha@gmail.com","9489696937",address);
            customer=Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha2003@gmail.com").address(address).build();
            mobileNumLoginDTO = new MobileNumLoginDTO("9489696937", "Suvetha@123");
            userNameLoginDTO = new UserNameLoginDTO("Suvetha", "Suvetha@123");
            updateDTO = new UpdateDTO(1,"Prathiksaa","Suvetha@123","suvetha@gmail.com","9489696937",true,address);

        }
        @AfterEach
        public void DeleteValue(){
            address=null;
            registerUserDTO=null;
            mobileNumLoginDTO = null;
            userNameLoginDTO = null;
            updateDTO = null;
        }
        @DisplayName("Customer Registration Test")
        @Test
        void CustomerRegistrationTest()
        {

            Cylinder cylinder=null;
            Booking booking=null;
            try {
                Assertions.assertNotNull(customerService.registerUser(customer));

            } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
                throw new RuntimeException(e);
            }
        }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithMobileNumberGreaterThan10Test()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("94896969385767").email("suvetha2003@gmail.com").address(address).build()));

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithMobileNumberGreaterThan10AssertEqualsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("94896969385767").email("suvetha2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter a valid Mobile Number (length=10)",e.getMessage());
        }

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithMobileNumberNotEqualTo10Test()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("94896969385767").email("suvetha2003@gmail.com").address(address).build()));

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithMobileNumberNotEqualTo10AssertEqualsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("94896969").email("suvetha2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter a valid Mobile Number",e.getMessage());
        }

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationMobileNumberAlreadyExistTest(){

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(customer=Customer.builder().userName("Prathiksaa").password("Prathi@123").mobileNo("9489696937").email("prathi2003@gmail.com").address(address).build()));

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationMobileNumberAlreadyExistAssertEqualsTest(){

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.registerUser(customer=Customer.builder().userName("Prathiksaa").password("Prathi@123").mobileNo("9489696937").email("prathi2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            Assertions.assertEquals("Mobile Number already exist",e.getMessage());
        }

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationUserNameAlreadyExistTest(){

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(customer=Customer.builder().userName("Suvetha").password("Prathi@123").mobileNo("9003428650").email("prathi2003@gmail.com").address(address).build()));

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationUserNameAlreadyExistAssertEqualsTest(){

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.registerUser(customer=Customer.builder().userName("Suvetha").password("Prathi@123").mobileNo("9003428650").email("prathi2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            Assertions.assertEquals("userName already exist",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithoutValidDetailsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(Customer.builder().userName("").password("").mobileNo("").email("suvetha2003@gmail.com").address(address).build()));
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithoutValidDetailsAssertEqualsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(customer=Customer.builder().userName("").password("Prathi@123").mobileNo("9003428650").email("prathi2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter a valid details to complete registration",e.getMessage());
        }

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithoutValidDetails2AssertEqualsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(customer=Customer.builder().userName("").password("Prathi@123").mobileNo("9003428650").email("prathi2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter a valid details to complete registration",e.getMessage());
        }

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationPasswordValidationTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(InvalidPasswordException.class,()->customerService.registerUser(Customer.builder().userName("prathiksaa").password("").mobileNo("9003428650").email("suvetha2003@gmail.com").address(address).build()));
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationPasswordValidationAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(customer=Customer.builder().userName("Prathi").password("Prathi").mobileNo("9003428650").email("prathi2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Password should not contain any space.Password should contain at least one digit(0-9).Password length should be between 8 to 15 characters.Password should contain at least one lowercase letter(a-z).Password should contain at least one uppercase letter(A-Z).Password should contain at least one special character ( @, #, %, &, !, $, etc….).",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationEmailValidationTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(InvalidEmailException.class,()->customerService.registerUser(Customer.builder().userName("prathiksaa").password("Prathi@573").mobileNo("9003428650").email("suvetha2003.com").address(address).build()));
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationEmailValidationAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(customer=Customer.builder().userName("Prathi").password("Prathi@573").mobileNo("9003428650").email("prathi2003.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter A valid Email Address",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerLoginUseMobileNumTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserMobileNo("9489696937", "Suvetha@123"));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUnRegisteredMobileNumLoginTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(CustomerException.class,()->customerService.loginUserMobileNo("9003428650","Prathi@573"));
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUnRegisteredLoginUseMobileNumAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.loginUserMobileNo("9003428650","Prathi@573");
        } catch (CustomerException e) {
            Assertions.assertEquals("Mobile Number doesn't exist, Login using registered Mobile Number",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerCredentialNonMatchMobileNumAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.loginUserMobileNo("9489696937","Prathi@573");
        } catch (CustomerException e) {
            Assertions.assertEquals("Credentials doesn't match",e.getMessage());
        }
    }
    //---
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerLoginUserNameTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName("Suvetha", "Suvetha@123"));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUnRegisteredUserNameLoginTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        Assertions.assertThrows(CustomerException.class,()->customerService.loginUserName("Suvetha","Prathi@573"));
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUnRegisteredLoginUseUserNameAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.loginUserName("prathi","Prathi@573");
        } catch (CustomerException e) {
            Assertions.assertEquals("Enter valid UserName",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerCredentialNonMatchUserNameAssertEqualsTest()
    {
        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));
        } catch (CustomerException | InvalidPasswordException | InvalidEmailException e) {
            throw new RuntimeException(e);
        }
        try {
            customerService.loginUserName("Suvetha","Prathi@573");
        } catch (CustomerException e) {
            Assertions.assertEquals("Credentials doesn't match",e.getMessage());
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUpdateTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));

        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }

        try {
            customerService.updateProfile(updateDTO);
        } catch (CustomerException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerUpdateInvalidIDTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            Assertions.assertNotNull(customerService.registerUser(customer));

        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            throw new RuntimeException(e);
        }

        try {
            UpdateDTO InvalidupdateDTO = new UpdateDTO(2, "Prathiksaa", "Suvetha@123", "suvetha@gmail.com", "9489696937", true, address);
            customerService.updateProfile(InvalidupdateDTO);
        } catch (CustomerException e) {
            Assertions.assertEquals("Enter valid Customer Details",e.getMessage());
        }
    }
    @DisplayName("Customer Test")
    @Test
    void getAllCylinderMedicalTest(){
            Assertions.assertNotNull(customerService.getAllCylindersOfSpecifiedType(CylinderType.Medical));
    }
    @DisplayName("Customer Test")
    @Test
    void getAllCylinderIndustryTest(){
        Assertions.assertNotNull(customerService.getAllCylindersOfSpecifiedType(CylinderType.Industry));
    }
    @DisplayName("Customer Test")
    @Test
    void getAllCylinderHouseHoldTest(){
        Assertions.assertNotNull(customerService.getAllCylindersOfSpecifiedType(CylinderType.HouseHold));
    }
}


