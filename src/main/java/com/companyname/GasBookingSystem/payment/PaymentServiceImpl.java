package com.companyname.GasBookingSystem.payment;

import com.companyname.GasBookingSystem.bank.Bank;
import com.companyname.GasBookingSystem.bank.BankRepository;
import com.companyname.GasBookingSystem.booking.Booking;
import com.companyname.GasBookingSystem.booking.BookingRepository;
import com.companyname.GasBookingSystem.booking.BookingStatusType;
import com.companyname.GasBookingSystem.customer.*;
import com.companyname.GasBookingSystem.customer.Exception.ViewCustomerProfileException;
import com.companyname.GasBookingSystem.customer.dto.ViewCustomerDTO;
import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderRepository;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderAddDTO;
import com.companyname.GasBookingSystem.bank.dto.BankUpdateDTO;
import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.payment.Exception.PaymentException;
import com.companyname.GasBookingSystem.payment.dto.PaymentUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private BankRepository bankRepository;
    @Autowired
    private CylinderRepository cylinderRepository;
    @Autowired
    private BookingRepository bookingRepository;
    @Autowired
    private PaymentRepository paymentRepository;
    @Override
    @Transactional
    public Booking paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException {
        Optional<Customer> customerEntityOptional=this.customerRepository.findById(paymentDTO.getCustomerId());
        if(customerEntityOptional.isEmpty()) throw new PaymentException("The account does not exists");
        Customer customerId=customerEntityOptional.get();
        if(customerId.getBookingList().isEmpty()) throw new PaymentException("The Booking list is empty! Add a cylinder to book");
        if(customerId.getBank()==null) throw new PaymentException("The Bank should be registered for the payment transaction");
        Optional<Bank> bankEntityOptional=this.bankRepository.findById(customerId.getBank().getBankId());
        Bank bankId=bankEntityOptional.get();
        if(bankId.getIsActive()==null) throw new PaymentException("The bank account is not active! Register the Bank account");
        Optional<Booking> bookingEntityOptional=this.bookingRepository.findById(paymentDTO.getBookingId());
        if(bookingEntityOptional.isEmpty()) throw new PaymentException("The entered Id doesn't exist enter an valid booking id");
        Booking bookingId=bookingEntityOptional.get();
        BookingStatusType bookingStatus=bookingId.getStatus();
        if(bookingStatus.equals(BookingStatusType.Booked)) throw new PaymentException("The payment for this booking is paid");
        Double price=bookingEntityOptional.get().getCylinder().getPrice();
        Payment paymentEntity=null;
        if(bookingId.getPayment()==null) {
            paymentEntity=new Payment();
            this.paymentRepository.save(paymentEntity);
            bookingId.setPayment(paymentEntity);
            this.bookingRepository.save(bookingId);
        }
        Optional<Payment> payment = this.paymentRepository.findById(bookingId.getPayment().getPaymentId());
        paymentEntity=payment.get();
        if(bankId.getBalance()<price){
            bookingId.setStatus(BookingStatusType.Pending);
            this.bookingRepository.save(bookingId);
            paymentEntity.setPaymentStatus(PaymentStatusType.Failed);
            this.paymentRepository.save(paymentEntity);
            throw new PaymentException("The account balance is insufficent");
        }
        Double balance=bankId.getBalance()-price;
        bankId.setBalance(balance);
        this.bankRepository.save(bankId);
        paymentEntity.setPaymentStatus(PaymentStatusType.Paid);
        paymentEntity.setPaymentDate(LocalDate.now());
        paymentEntity.setPaymentType("Online Mode");
        paymentEntity.setPaymentAmount(price);
        bookingId.setStatus(BookingStatusType.Booked);
        bookingId.setBookingDate(LocalDate.now());
        bookingId.setDeliveryDate(LocalDate.now().plusDays(3));
        bookingId.setPayment(paymentEntity);
        this.paymentRepository.save(paymentEntity);
        this.customerRepository.save(customerId);
        return this.bookingRepository.save(bookingId);

    }

    @Override
    public Customer getCustomer(Integer id) {
        Optional<Customer> customer= this.customerRepository.findById(id);
        return customer.get();
    }

    @Override
    public Customer updateBankAccount(BankUpdateDTO bankUpdateDTO) {
        Optional<Customer> customer=this.customerRepository.findById(bankUpdateDTO.getCustomerId());
        Customer customer1 =customer.get();
        Bank bank=new Bank();
        bank.setAccountNo(bankUpdateDTO.getAccountNo());
        bank.setBankName(bankUpdateDTO.getBankName());
        bank.setBranch(bankUpdateDTO.getBankName());
        bank.setBalance(bankUpdateDTO.getBalance());
        bank.setIsActive(Boolean.TRUE);
        customer1.setBank(bank);
        bank.setCustomer(customer1);
        this.bankRepository.save(bank);
        return this.customerRepository.save(customer1);

    }

    @Override
    public ViewCustomerDTO viewProfile(Integer id) throws ViewCustomerProfileException {
        ViewCustomerDTO viewCustomerDTO =new ViewCustomerDTO();
        Optional<Customer> viewuser=this.customerRepository.findById(id);
        if(viewuser.isEmpty()) throw new ViewCustomerProfileException("The Entered Id doesn't exists! Enter an valid Id");
        Customer customer=viewuser.get();
        viewCustomerDTO.setId(customer.getId());
        viewCustomerDTO.setBank(customer.getBank());
        viewCustomerDTO.setUserName(customer.getUserName());
        viewCustomerDTO.setMybookings(customer.getBookingList());
        viewCustomerDTO.setEmail(customer.getEmail());
        viewCustomerDTO.setIsActive(customer.isIsActive());
        viewCustomerDTO.setAddress(customer.getAddress());
        viewCustomerDTO.setMobileNo(customer.getMobileNo());
        return viewCustomerDTO;

    }
    @Override
    public List<Payment> getTransactions(Integer id) throws PaymentException {
        Optional<Customer> customerOptional=this.customerRepository.findById(id);
        if(customerOptional.isEmpty()) throw new PaymentException("Enter an valid Id");
        Customer customerid=customerOptional.get();
        List<Booking> bookingList=customerid.getBookingList();
        if(bookingList.isEmpty()) throw new PaymentException("No bookings were made");
        List<Payment> paymentList=new ArrayList<>();
        for(Booking bookings: bookingList) paymentList.add(bookings.getPayment());
        return paymentList;

    }
}
