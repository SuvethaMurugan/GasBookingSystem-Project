package com.companyname.gasbookingsystem.customer.dto;

import com.companyname.gasbookingsystem.address.Address;
import com.companyname.gasbookingsystem.bank.Bank;
import com.companyname.gasbookingsystem.booking.Booking;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.validation.constraints.NotNull;
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
    @NotNull(message = "ID should not be null")
    private Integer id;
    @NotNull(message = "UserName should not be null")
    private String userName;
    @NotNull(message = "Email should not be null")
    private String email;
    @NotNull(message = "Mobile num should not be null")
    private String mobileNo;
    @OneToOne
    @NotNull(message = "Address should not be null")
    private Address address;
    @NotNull(message = "IsActive should not be null")
    private boolean isActive;
    @OneToOne
    private Bank bank;
    @OneToMany
    private List<Booking> mybookings;


}
