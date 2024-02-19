package com.companyname.GasBookingSystem.customer.dto;

import com.companyname.GasBookingSystem.bank.Bank;
import com.companyname.GasBookingSystem.booking.Booking;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ViewCustomerDTO {
    private Integer id;
    private String userName;
    private String email;
    private String mobileNo;
    private boolean IsActive;
    @OneToOne
    private Bank bank;
    @OneToMany
    private List<Booking> Mybookings;
}
