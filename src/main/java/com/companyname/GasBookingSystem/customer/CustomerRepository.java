package com.companyname.GasBookingSystem.customer;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CustomerRepository extends JpaRepository<Customer,Integer>{
    Customer findByMobileNo(String mobileNo);
    Customer findByUserName(String userName);

    Customer findByUserNameAndPassword(String userName, String password);

    Customer findByMobileNoAndPassword(String mobileNo, String password);
}
