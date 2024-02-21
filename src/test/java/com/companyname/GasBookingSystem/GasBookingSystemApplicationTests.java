package com.companyname.GasBookingSystem;

import com.companyname.GasBookingSystem.admin.AdminService;
import com.companyname.GasBookingSystem.customer.Customer;
import com.companyname.GasBookingSystem.customer.CustomerService;
import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class GasBookingSystemApplicationTests {

	@Autowired
	private CustomerService customerService;

	@Test
	public void registerCustomerTest()  {
        try {
            Assertions.assertNotNull(this.customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha@gmail.com").build()));
        } catch (CustomerException e) {
            Assertions.fail(e.getMessage());
        }
    }
	@Test
	public void LoginUsingUserName()  {
		Customer customer=null;
		try {
			customer=this.customerService.registerUser(Customer.builder().userName("Suvetha").password("Suvetha@123").mobileNo("9489696937").email("suvetha@gmail.com").build());
			Assertions.assertNotNull(this.customerService.loginUserName(customer.getUserName(), customer.getPassword()));
		} catch (CustomerException e) {
			Assertions.fail(e.getMessage());
		}
	}

}
