package com.companyname.gasbookingsystem.payment;

import com.companyname.gasbookingsystem.bank.Bank;
import com.companyname.gasbookingsystem.bank.BankRepository;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingRepository;
import com.companyname.gasbookingsystem.booking.BookingStatusType;
import com.companyname.gasbookingsystem.customer.*;
import com.companyname.gasbookingsystem.customer.exception.ViewCustomerProfileException;
import com.companyname.gasbookingsystem.customer.dto.ViewCustomerDTO;
import com.companyname.gasbookingsystem.bank.dto.BankUpdateDTO;
import com.companyname.gasbookingsystem.payment.exception.BankUpdateException;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final CustomerRepository customerRepository;
    private final BankRepository bankRepository;
    private final BookingRepository bookingRepository;
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(CustomerRepository customerRepository, BankRepository bankRepository,BookingRepository bookingRepository, PaymentRepository paymentRepository) {
        this.customerRepository = customerRepository;
        this.bankRepository = bankRepository;
        this.bookingRepository = bookingRepository;
        this.paymentRepository = paymentRepository;
    }

    @Override
    @Transactional
    public Booking paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException {
        Optional<Customer> customerEntityOptional=this.customerRepository.findById(paymentDTO.getCustomerId());
        if(customerEntityOptional.isEmpty()) throw new PaymentException("The account does not exists");
        Customer customerId=customerEntityOptional.get();
        if(customerId.getBookingList().isEmpty()) throw new PaymentException("The Booking list is empty! Add a cylinder to book");
        if(customerId.getBank()==null) throw new PaymentException("The Bank should be registered for the payment transaction");
        Optional<Bank> bankEntityOptional=this.bankRepository.findById(customerId.getBank().getBankId());
        if(bankEntityOptional.isEmpty()) throw new PaymentException("The bank ID id not found! Create a bank Account");
        Bank bankId=bankEntityOptional.get();
        Optional<Booking> bookingEntityOptional=this.bookingRepository.findById(paymentDTO.getBookingId());
        if(bookingEntityOptional.isEmpty()) throw new PaymentException("The entered Id doesn't exist for the Customer! Enter an valid booking id");
        Booking bookingId=bookingEntityOptional.get();
        BookingStatusType bookingStatus=bookingId.getStatus();
        if(bookingStatus.equals(BookingStatusType.BOOKED)) throw new PaymentException("The payment for this booking is paid");
        Double price=bookingEntityOptional.get().getCylinder().getPrice();
        Payment paymentEntity=null;
        if(bookingId.getPayment()==null) {
            paymentEntity=new Payment();
            this.paymentRepository.save(paymentEntity);
            bookingId.setPayment(paymentEntity);
            this.bookingRepository.save(bookingId);
        }
        Optional<Payment> payment = this.paymentRepository.findById(bookingId.getPayment().getPaymentId());
        if(payment.isEmpty()) throw new PaymentException("The payment ID is not found for the particular Customer");
        paymentEntity=payment.get();
        if(bankId.getBalance()<price){
            bookingId.setStatus(BookingStatusType.PENDING);
            this.bookingRepository.save(bookingId);
            paymentEntity.setPaymentStatus(PaymentStatusType.FAILED);
            this.paymentRepository.save(paymentEntity);
            throw new PaymentException("The account balance is insufficent");
        }
        Double balance=bankId.getBalance()-price;
        bankId.setBalance(balance);
        this.bankRepository.save(bankId);
        paymentEntity.setPaymentStatus(PaymentStatusType.PAID);
        paymentEntity.setPaymentDate(LocalDate.now());
        paymentEntity.setPaymentType("Online Mode");
        paymentEntity.setPaymentAmount(price);
        bookingId.setStatus(BookingStatusType.BOOKED);
        bookingId.setBookingDate(LocalDate.now());
        bookingId.setDeliveryDate(LocalDate.now().plusDays(3));
        bookingId.setPayment(paymentEntity);
        this.paymentRepository.save(paymentEntity);
        this.customerRepository.save(customerId);
        return this.bookingRepository.save(bookingId);

    }

    @Override
    public Customer updateBankAccount(BankUpdateDTO bankUpdateDTO)  throws BankUpdateException {
        Optional<Customer> customer=this.customerRepository.findById(bankUpdateDTO.getCustomerId());
        if(customer.isEmpty()) throw new BankUpdateException("The customer is not found for the entered ID");
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
        viewCustomerDTO.setActive(customer.isActive());
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
