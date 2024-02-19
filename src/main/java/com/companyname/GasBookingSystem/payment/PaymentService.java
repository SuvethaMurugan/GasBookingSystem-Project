package com.companyname.GasBookingSystem.payment;

import com.companyname.GasBookingSystem.customer.Customer;
import com.companyname.GasBookingSystem.customer.dto.ViewCustomerDTO;
import com.companyname.GasBookingSystem.customer.Exception.ViewCustomerProfileException;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderAddDTO;
import com.companyname.GasBookingSystem.bank.dto.BankUpdateDTO;
import com.companyname.GasBookingSystem.booking.Booking;
import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.payment.Exception.PaymentException;
import com.companyname.GasBookingSystem.payment.dto.PaymentUpdateDTO;

public interface PaymentService {
     Customer createuser(Customer customer);

     Cylinder addCylinder(Cylinder cylinder);
     Customer addCylinderToCustomer(CylinderAddDTO cylinderAddDTO) throws AddCylinderException;
     Booking paymentCylinder(PaymentUpdateDTO paymentDTO) throws PaymentException;

     Customer getCustomer(Integer id);

     Customer updateBankAccount(BankUpdateDTO bankUpdateDTO);

     ViewCustomerDTO viewProfile(Integer id) throws ViewCustomerProfileException;
}
