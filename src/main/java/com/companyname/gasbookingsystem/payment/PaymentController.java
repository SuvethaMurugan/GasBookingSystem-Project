package com.companyname.gasbookingsystem.payment;

import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.customer.dto.ViewCustomerDTO;
import com.companyname.gasbookingsystem.customer.exception.ViewCustomerProfileException;
import com.companyname.gasbookingsystem.bank.dto.BankUpdateDTO;
import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.payment.dto.BankLinkingDTO;
import com.companyname.gasbookingsystem.payment.exception.BankUpdateException;
import com.companyname.gasbookingsystem.payment.exception.PaymentException;
import com.companyname.gasbookingsystem.payment.dto.PaymentUpdateDTO;
import jakarta.validation.Valid;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://localhost:4200", "http://localhost:3000"})
public class PaymentController {
    private final PaymentService paymentService;
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping("/book/payment")
    public Booking paymentForCylinder(@Valid @RequestBody PaymentUpdateDTO paymentDTO) throws PaymentException {
        return this.paymentService.paymentCylinder(paymentDTO);
    }
    @PatchMapping("/bank")
    public Customer updateBankAccount(@Valid @RequestBody BankLinkingDTO bankUpdateDTO) throws BankUpdateException {
        return this.paymentService.bankLinkingAccount(bankUpdateDTO);
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
