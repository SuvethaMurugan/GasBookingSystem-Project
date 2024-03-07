package com.companyname.gasbookingsystem;
import com.companyname.gasbookingsystem.admin.Admin;
import com.companyname.gasbookingsystem.admin.AdminRepository;
import com.companyname.gasbookingsystem.admin.AdminService;
import com.companyname.gasbookingsystem.admin.DTO.AdminEmailDto;
import com.companyname.gasbookingsystem.admin.DTO.AdminLoginDTO;
import com.companyname.gasbookingsystem.admin.exception.AdminException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
 class AdminTest {
    @Autowired
    private AdminService adminService;
    @Autowired
            private AdminRepository adminRepository;
    Admin admin;
    AdminLoginDTO adminLoginDTO;
    AdminEmailDto adminEmailDto;

    @BeforeEach
    public void createCustomer(){
        admin = new Admin(1,"Selciyaa","stalink@ford.com","suvethakk");
        adminRepository.save(admin);
        adminLoginDTO = new AdminLoginDTO(admin.getAdminId(), admin.getPassword());
        adminEmailDto = new AdminEmailDto("stalink@ford.com","suvethakk");

    }
    @AfterEach
    public void DeleteValue(){
        admin=null;
        adminLoginDTO=null;
        adminEmailDto=null;
        adminRepository.deleteAll();
    }
    @DisplayName("Admin Registration Test")
    @Test
    void AdminRegistrationTest()
    {
        try {
            Assertions.assertNotNull(adminService.loginAdminID(adminLoginDTO));
        } catch (AdminException e) {
            Assertions.fail(e.getMessage());
        }

    }
    @DisplayName("Admin Email Login Test")
    @Test
    void AdminEmailLoginTest()
    {
        try{
            Assertions.assertNotNull(adminService.loginAdminEmail(adminEmailDto));
        } catch (AdminException e) {
            throw new RuntimeException(e);
        }
    }
    @DisplayName("Admin Logout")
    @Test
    void AdminLogout(){
            Assertions.assertNotNull(adminService.adminLogout(adminEmailDto));
    }
    @DisplayName("List of Cylinders")
    @Test
    void getListOfCylinders(){
        Assertions.assertNotNull(adminService.getAllListOfCylinders());
    }
    @DisplayName("List of Customers")
    @Test
    void getListOfCustomers(){
        Assertions.assertNotNull(adminService.getAllCustomers());
    }
}
