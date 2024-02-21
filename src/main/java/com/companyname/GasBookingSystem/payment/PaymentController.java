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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @PostMapping("/book/payment")
    public Booking paymentForCylinder(@RequestBody PaymentUpdateDTO paymentDTO) throws PaymentException {
        return this.paymentService.paymentCylinder(paymentDTO);
    }
    @PatchMapping("/bank")
    public Customer updateBankAccount(@RequestBody BankUpdateDTO bankUpdateDTO){
        return this.paymentService.updateBankAccount(bankUpdateDTO);
    }
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id){
        return this.paymentService.getCustomer(id);
    }
    @GetMapping("/transaction/{id}")
    public List<Payment> getTransactions(@PathVariable("id") Integer id) throws PaymentException{
        return this.paymentService.getTransactions(id);
    }
    @GetMapping("profile/{id}")
    public ViewCustomerDTO viewProfile(@PathVariable("id") Integer id) throws ViewCustomerProfileException {
        return this.paymentService.viewProfile(id);
    }
}
