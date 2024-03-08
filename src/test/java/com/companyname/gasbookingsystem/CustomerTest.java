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
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class CustomerTest {

    @Autowired
    private CustomerService customerService;
    Customer customer;

    Address address;
    MobileNumLoginDTO mobileNumLoginDTO;
    UserNameLoginDTO userNameLoginDTO;
    UpdateDTO updateDTO;
    RegisterUserDTO registerUserDTO;


    @BeforeEach
    public void createCustomer(){
        address=new Address(1,12,"StreetName","Chennai","6000001");
        registerUserDTO=new RegisterUserDTO("Suvetha","Suvetha@123","suvetha@gmail.com","9489696937",address);
        customer=Customer.builder().userName("Suvetha").password("suvetha2003@gmail.com").address(address).build();
        mobileNumLoginDTO = new MobileNumLoginDTO("9489696937", "Suvetha@123");
        userNameLoginDTO = new UserNameLoginDTO("Suvetha", "Suvetha@123");
        updateDTO = new UpdateDTO(1,"Suvetha","Suvetha@123","suvetha@gmail.com","9489696937",true,address);
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
        Assertions.assertThrows(CustomerException.class,()->customerService.registerUser(Customer.builder().userName("Suvetha").password("suvetha2003@gmail.com").address(address).build()));

    }
    @DisplayName("Customer Registration Test")
    @Test
    void CustomerRegistrationWithMobileNumberGreaterThan10AssertEqualsTest()
    {

        Cylinder cylinder=null;
        Booking booking=null;
        try {
            customerService.registerUser(Customer.builder().userName("Suvetha").password("suvetha2003@gmail.com").address(address).build());
        } catch (CustomerException | InvalidEmailException | InvalidPasswordException e) {
            Assertions.assertEquals("Enter a valid Mobile Number",e.getMessage());
        }

    }

}


