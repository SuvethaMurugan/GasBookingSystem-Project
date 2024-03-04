package com.companyname.gasbookingsystem.customer;


import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderRepository;
import com.companyname.gasbookingsystem.cylinder.CylinderType;

import java.util.List;
    
    
import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.address.AddressRepository;
import com.companyname.gasbookingsystem.customer.exception.CustomerException;
import com.companyname.gasbookingsystem.customer.exception.InvalidEmailException;
import com.companyname.gasbookingsystem.customer.exception.InvalidPasswordException;
import com.companyname.gasbookingsystem.customer.dto.UpdateDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


@Service
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final AddressRepository addressRepository;
    private final CylinderRepository cylinderRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository, AddressRepository addressRepository, CylinderRepository cylinderRepository) {
        this.customerRepository = customerRepository;
        this.addressRepository = addressRepository;
        this.cylinderRepository = cylinderRepository;
    }

    @Override
    public Customer registerUser(Customer registeruser) throws CustomerException, InvalidPasswordException, InvalidEmailException {
        Customer mobileNum= this.customerRepository.findByMobileNo(registeruser.getMobileNo());
        System.out.println(mobileNum);
        Customer nameLogin= this.customerRepository.findByUserName(registeruser.getUserName());
        String mobileNumLength = registeruser.getMobileNo();
        if (mobileNumLength.length() > 10){
            throw new CustomerException("Enter a valid Mobile Number");
        }
        if(mobileNum != null ) {
            throw new CustomerException("Mobile Number already exist");
        }
        if ((nameLogin != null)) throw new CustomerException("userName already exist");
        if(registeruser.getUserName() != null && registeruser.getPassword() != null && registeruser.getMobileNo() != null){
            Customer customerEntity = new Customer();
            customerEntity.setUserName(registeruser.getUserName());
            customerEntity.setPassword(registeruser.getPassword());
            passwordValidator(customerEntity.getPassword());
            customerEntity.setMobileNo(registeruser.getMobileNo());
            customerEntity.setEmail(registeruser.getEmail());
            emailValidator(customerEntity.getEmail());
            customerEntity.setActive(Boolean.TRUE);
            Address address = new Address();
            this.addressRepository.save(address);
            customerEntity.setAddress(address);
            address.setDoorNo(registeruser.getAddress().getDoorNo());
            address.setStreetName(registeruser.getAddress().getStreetName());
            address.setCity(registeruser.getAddress().getCity());
            address.setPinCode(registeruser.getAddress().getPinCode());
            this.addressRepository.save(address);
            return customerRepository.save(customerEntity);
        }else{
            return null;
        }
    }
    public boolean passwordValidator(String password) throws InvalidPasswordException {
        String regex = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(password);
        if(matcher.matches()){
            return true;
        }else{
            throw new InvalidPasswordException("Password should not contain any space.Password should contain at least one digit(0-9).Password length should be between 8 to 15 characters.Password should contain at least one lowercase letter(a-z).Password should contain at least one uppercase letter(A-Z).Password should contain at least one special character ( @, #, %, &, !, $, etc….).");
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
    public Customer loginUserMobileNo(String mobileNo, String password) throws CustomerException {
        Customer mobileNumLogin = this.customerRepository.findByMobileNo(mobileNo);
        if(mobileNumLogin == null) throw new CustomerException("Mobile Number doesn't exist, Login using registered Mobile Number");
        if(mobileNumLogin.equals(mobileNo) && mobileNumLogin.equals(password)){
            return this.customerRepository.findByMobileNoAndPassword(mobileNo,password);
        }
        else if (mobileNumLogin != null && mobileNumLogin.getPassword().equals(password)) {
            return this.customerRepository.findByMobileNo(mobileNo);
        }else{
            throw new CustomerException("Credentials doesn't match");
        }
    }

    public Customer loginUserName(String userName, String password) throws CustomerException {
        Customer nameLogin= this.customerRepository.findByUserName(userName);
        if(userName.isEmpty() || nameLogin == null) throw new CustomerException("Enter valid UserName");
        if (nameLogin.equals(userName) && nameLogin.equals(password)) {
            return this.customerRepository.findByUserNameAndPassword(userName, password);
        }
        else if (nameLogin != null && nameLogin.getPassword().equals(password)) {
            return this.customerRepository.findByUserName(userName);
        }
        else{
            throw new CustomerException("Credentials doesn't match");
        }
    }
    @Override
    public List<Cylinder> getAllCylindersOfMedical(CylinderType type) {
         return this.cylinderRepository.findAllByType(type);
    }
    @Override
    public Customer updateProfile(UpdateDTO updateAccount) throws CustomerException {
        if (customerRepository.existsById(updateAccount.getId())){
            Customer customerEntity = customerRepository.getReferenceById(updateAccount.getId());
            customerEntity.setUserName(updateAccount.getUserName());
            customerEntity.setEmail(updateAccount.getEmail());
            customerEntity.setPassword(updateAccount.getPassword());
            customerEntity.setMobileNo(updateAccount.getMobileNo());
            customerEntity.setActive(updateAccount.isActive());
            Address address = new Address();
            address.setDoorNo(updateAccount.getAddress().getDoorNo());
            address.setStreetName(updateAccount.getAddress().getStreetName());
            address.setCity(updateAccount.getAddress().getCity());
            address.setPinCode(updateAccount.getAddress().getPinCode());
            this.addressRepository.save(address);
            customerEntity.setAddress(address);
            return customerRepository.save(customerEntity);
        }else {
            throw new CustomerException("Enter valid Customer Details");
        }
    }


}

