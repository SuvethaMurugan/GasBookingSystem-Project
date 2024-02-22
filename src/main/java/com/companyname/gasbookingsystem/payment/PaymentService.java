package com.companyname.gasbookingsystem.payment;

import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.dto.ViewCustomerDTO;
import com.companyname.gasbookingsystem.customer.exception.ViewCustomerProfileException;
import com.companyname.gasbookingsystem.bank.dto.BankUpdateDTO;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.payment.exception.BankUpdateException;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;

import java.util.List;

public interface PaymentService {
     Booking paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException;

     Customer updateBankAccount(BankUpdateDTO bankUpdateDTO) throws BankUpdateException;

     ViewCustomerDTO viewProfile(Integer id) throws ViewCustomerProfileException;

     List<Payment> getTransactions(Integer id)throws PaymentException;
}
