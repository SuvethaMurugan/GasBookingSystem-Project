package com.companyname.GasBookingSystem.customer;


import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderRepository;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
    
    
import com.companyname.GasBookingSystem.address.Address;
import com.companyname.GasBookingSystem.address.AddressRepository;
import com.companyname.GasBookingSystem.customer.Exception.CustomerException;
import com.companyname.GasBookingSystem.customer.Exception.InvalidEmailException;
import com.companyname.GasBookingSystem.customer.Exception.InvalidPasswordException;
import com.companyname.GasBookingSystem.customer.dto.UpdateDTO;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private AddressRepository addressRepository;
    @Autowired
    private CylinderRepository cylinderRepository;
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
            customerEntity.setEmail(registeruser.getEmail());
            customerEntity.setIsActive(Boolean.TRUE);
            Address address = new Address();
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
    public boolean passwordValidator(String password) throws InvalidPasswordException {
        String regex = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            throw new InvalidPasswordException("Password should not contain any space.\n" +
                    "Password should contain at least one digit(0-9).\n" +
                    "Password length should be between 8 to 15 characters.\n" +
                    "Password should contain at least one lowercase letter(a-z).\n" +
                    "Password should contain at least one uppercase letter(A-Z).\n" +
                    "Password should contain at least one special character ( @, #, %, &, !, $, etc….).");
        }
    }

    public boolean emailValidator(String email) throws InvalidEmailException {
        String regex = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        if(matcher.matches()){
            return true;
        }else{
            throw new InvalidEmailException("Enter A valid Email Address");
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
        if (nameLogin != null && nameLogin.getPassword().equals(password)) {
            return this.customerRepository.findByUserName(userName);
        }
        return null;
    }
    @Override
    public List<Cylinder> getAllCylindersOfMedical(CylinderType type) {
         return this.cylinderRepository.findAllByType(type);
    }
    @Override
    public Customer updateProfile(UpdateDTO updateAccount) {
        if (customerRepository.existsById(updateAccount.getId())){
            Customer customerEntity = customerRepository.getReferenceById(updateAccount.getId());
            customerEntity.setUserName(updateAccount.getUserName());
            customerEntity.setEmail(updateAccount.getEmail());
            customerEntity.setPassword(updateAccount.getPassword());
            customerEntity.setMobileNo(updateAccount.getMobileNo());
            customerEntity.setIsActive(updateAccount.isIsActive());
            return customerRepository.save(customerEntity);
        }else {
            System.out.println("Enter valid Details");
        }
        return null;
    }
}

