package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.bank.BankService;
import com.companyname.gasbookingsystem.bank.dto.CreateBankDTO;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingService;
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
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderService;
import com.companyname.gasbookingsystem.cylinder.CylinderServiceImpl;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import com.companyname.gasbookingsystem.cylinder.dto.BookedCylinderDTO;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderAddDTO;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import com.companyname.gasbookingsystem.payment.PaymentService;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CylinderTest {
    @Autowired
    private CylinderService cylinderService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private BookingService bookingService;


    CylinderDTO cylinderDTO;
    Customer customer;
    RegisterUserDTO registerUserDTO;
    Address address;
    CreateBankDTO createBankDTO;
    CreateBankDTO createBankDTO1;
    Cylinder cylinder;
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

    @Test
    public void addCylinderTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertNotNull(cylinderService.addCylinder(cylinderDTO));
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Cylinder WeightNull Test")
    public void CylinderWeightNullTest()
    {
        CylinderDTO cylinderDTO = new CylinderDTO(CylinderType.Medical, null, 500.0);
        Assertions.assertThrows(AddCylinderException.class, () -> cylinderService.addCylinder(cylinderDTO));
    }
    @Test
    @DisplayName("Cylinder Price Test")
    public void CylinderPriceTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            Assertions.assertNotNull(cylinderService.addCylinder(cylinderDTO));
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Cylinder PriceNull Test")
    public void CylinderPriceNullTest()
    {
        try {
            CylinderDTO cylinderDTO = new CylinderDTO(CylinderType.Medical, 5.0, null);
            Assertions.assertThrows(AddCylinderException.class,()->cylinderService.addCylinder(cylinderDTO));
        }
        catch(Exception e)
        {
            Assertions.assertEquals("Cylinder price cant be null",e.getMessage());
        }
    }
    @Test
    @DisplayName("GetCylinderById Test")
    public void getCylinderByIdTest()
    {

        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);

        try {

            cylinder= cylinderService.addCylinder(cylinderDTO);
            Assertions.assertNotNull(cylinderService.getCylinderById(cylinder.getCylinderId()));
        }
        catch(GetCylinderException|AddCylinderException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("GetCylinderBYID Null Test")
    public void getCylinderByIdNullTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try
        {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(GetCylinderException.class,()->cylinderService.getCylinderById(null));

        } catch (AddCylinderException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Update CylinderByWeight Test")
    public void updateCylinderByWeightTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try
        {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(5.0,cylinderService.updateCylinder(cylinder).getWeight());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (UpdateCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("UpdateCylinderWeight less than 5kg Test")
    public void updateCylinderWeightLessThan5kgTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,4.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(UpdateCylinderException.class,
                    ()->cylinderService.updateCylinder(cylinder).getWeight());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Update CylinderPrice Test")
    public void updateCylinderPriceTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(500.0,cylinderService.updateCylinder(cylinder).getPrice());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (UpdateCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test

    @DisplayName("UpdateCylinderPrice Negative Test")
    public void updateCylinderPriceNegativeTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,-1.0);
      try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(UpdateCylinderException.class,
                    ()->cylinderService.updateCylinder(cylinder).getPrice());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @DisplayName("DeleteCylinder Test")
    public void deleteCylinderTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(1,cylinderService.deleteCylinder(cylinder.getCylinderId()).getCylinderId());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (DeleteCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("DeleteCylinderId Not found Test")
    public void deleteCylinderIdNotFoundTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(DeleteCylinderException.class,
                    ()->cylinderService.deleteCylinder(2).getCylinderId());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    void addAvailableCylinderTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinderService.addCylinder(cylinderDTO);
            Assertions.assertNotNull(cylinderService.availableCylinder());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("BookingCylinderListTest")
    @Test
    void BookingCylinderListTest()
    {

        Cylinder cylinder;
        Booking booking;
        try {
            customer=customerService.registerUser(customer);
            Assertions.assertNotNull(customerService.loginUserName(customer.getUserName(), customer.getPassword()));
            cylinder=cylinderService.addCylinder(cylinderDTO);
            BookingDTO bookingDTO=new BookingDTO(customer.getId(), cylinder.getCylinderId());
            booking=bookingService.createBooking(bookingDTO);
            Assertions.assertNotNull(cylinderService.BookedCylinder());
        }
        catch (CustomerException | InvalidPasswordException | BookingNotFoundException | CustomerNotExistsWithId | AddCylinderException |
               InvalidEmailException e) {
            Assertions.fail(e.getMessage());
        } catch (CylinderNotExistsWithId e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    void addAvailableCylinderNullTest()
    {
        List<Cylinder> list=new ArrayList<>();
        Assertions.assertEquals(cylinderService.availableCylinder(),list);

    }
    @DisplayName("BookingCylinderListTest")
    @Test
    void BookingCylinderListNullTest()
    {
        List<BookedCylinderDTO> list=new ArrayList<>();
        Assertions.assertEquals(cylinderService.BookedCylinder(),list);
    }
    @DisplayName("BookingCylinderListTest")
    @Test
    void BookingCylinderGetCylinderTest()
    {
       Assertions.assertThrows(GetCylinderException.class,()->cylinderService.getCylinderById(200));
    }




}
