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

@RestController
@RequestMapping("/users")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;

    @PostMapping("/create")
    public Customer createUser(@RequestBody Customer customer){
        return this.paymentService.createuser(customer);
    }
    @PostMapping("/book")
    public Cylinder createUser(@RequestBody Cylinder cylinder){
        return this.paymentService.addCylinder(cylinder);
    }
    @PostMapping("/addcylinder")
    public Customer addcylinderToUser(@RequestBody CylinderAddDTO cylinderAddDTO) throws AddCylinderException {
        return this.paymentService.addCylinderToCustomer(cylinderAddDTO);
    }
    @PostMapping("/book/payment")
    public Booking paymentForCylinder(@RequestBody PaymentUpdateDTO paymentDTO) throws PaymentException {
        return this.paymentService.paymentCylinder(paymentDTO);
    }
    @PatchMapping("/update/bank")
    public Customer updateBankAccount(@RequestBody BankUpdateDTO bankUpdateDTO){
        return this.paymentService.updateBankAccount(bankUpdateDTO);
    }
    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Integer id){

        return this.paymentService.getCustomer(id);
    }
    @GetMapping("viewprofile/{id}")
    public ViewCustomerDTO viewProfile(@PathVariable("id") Integer id) throws ViewCustomerProfileException {
        return this.paymentService.viewProfile(id);
    }
}
