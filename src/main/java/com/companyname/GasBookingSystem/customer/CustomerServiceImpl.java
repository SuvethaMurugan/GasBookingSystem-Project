package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.address.Address;
import com.companyname.GasBookingSystem.address.AddressRepository;
import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.login.AccountException;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Customer registerUser(Customer registeruser) throws CustomerException {
        Customer mobileNum= this.customerRepository.findByMobileNo(registeruser.getMobileNo());
        Customer nameLogin= this.customerRepository.findByUserName(registeruser.getUserName());
        if(mobileNum != null) {
            throw new CustomerException("Mobile Number already exist");
        }
        if ((nameLogin != null)) throw new CustomerException("userName already exist");
        if(registeruser.getUserName() != null && registeruser.getPassword() != null && registeruser.getMobileNo() != null){
            Customer customerEntity = new Customer();
            customerEntity.setUserName(registeruser.getUserName());
            customerEntity.setPassword(registeruser.getPassword());
            customerEntity.setMobileNo(registeruser.getMobileNo());
            customerEntity.setIsActive(Boolean.TRUE);
            Address address = new Address();
            //address.setId(registeruser.getId());
            address.setDoorNo(address.getDoorNo());
            address.setStreetName(address.getStreetName());
            address.setCity(address.getCity());
            address.setPinCode(address.getPinCode());
            this.addressRepository.save(address);
            customerEntity.setAddress(address);
            return customerRepository.save(customerEntity);
        }else{
            return null;
        }
    }


    public Customer loginUserMobileNo(Customer loginMobile) throws CustomerException {
        Customer mobileNum = this.customerRepository.findByMobileNo(loginMobile.getMobileNo());
        if(mobileNum == null) throw new CustomerException("Mobile Number doesn't exist, Login using registered Mobile Number");
        Customer numberLogin = this.customerRepository.findByMobileNo(loginMobile.getMobileNo());
        if (numberLogin != null && numberLogin.getPassword().equals(loginMobile.getPassword())) {
            return this.customerRepository.findByMobileNo(loginMobile.getMobileNo());
        }
        return null;
    }
    public Customer loginUserName(String userName, String password) throws CustomerException {
        Customer nameLogin= this.customerRepository.findByUserName(userName);
        if(userName.isEmpty()) throw new CustomerException("Enter valid UserName");
       // Customer nameLogin = CustomerRepository.findByUserName(userName);
        if (nameLogin != null && nameLogin.getPassword().equals(password)) {
            return this.customerRepository.findByUserName(userName);
        }
        return null;
    }

    @Override
    public Customer updateProfile(UpdateDTO updateAccount) {
        if (customerRepository.existsById(updateAccount.getId())){
            Customer customerEntity = customerRepository.getReferenceById(updateAccount.getId());
            customerEntity.setUserName(updateAccount.getUserName());
            customerEntity.setEmail(updateAccount.getEmail());
            customerEntity.setPassword(updateAccount.getEmail());
            customerEntity.setMobileNo(updateAccount.getMobileNo());
            customerEntity.setIsActive(updateAccount.isIsActive());
            customerRepository.save(customerEntity);
        }else {
            System.out.println("Enter valid Details");
        }
        return null;
    }
}

