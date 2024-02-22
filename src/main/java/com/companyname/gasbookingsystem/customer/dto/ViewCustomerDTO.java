package com.companyname.gasbookingsystem.customer.dto;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.bank.Bank;
import com.companyname.gasbookingsystem.booking.Booking;
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
    @OneToOne
    private Address address;
    private boolean isActive;
    @OneToOne
    private Bank bank;
    @OneToMany
    private List<Booking> mybookings;
}
