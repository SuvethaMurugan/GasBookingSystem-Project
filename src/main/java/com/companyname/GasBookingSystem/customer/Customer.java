package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.address.Address;
import com.companyname.GasBookingSystem.bank.Bank;

import com.companyname.GasBookingSystem.booking.Booking;
import jakarta.persistence.*;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.List;
import java.util.Objects;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Customer {
    @Id
    @GeneratedValue(strategy =GenerationType.AUTO)
    @Column(name="Customer_ID")
    private Integer id;
    @Column(name="User_Name")
    private String userName;
    @Column(name="Password")
    private String password;
    @Column(name="Email_ID")
    private String email;
    @Column(name="Mobile_No")
    @Size(min = 0, max = 10)
    private String mobileNo;
    @Column(name="IsActive")
    private boolean IsActive;
    @OneToOne
    private Address address;
    @OneToOne
    private Bank bank;
    @OneToMany
    private List<Booking> bookingList;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Customer customer = (Customer) o;
        return IsActive == customer.IsActive && Objects.equals(id, customer.id) && Objects.equals(userName, customer.userName) && Objects.equals(password, customer.password) && Objects.equals(email, customer.email) && Objects.equals(mobileNo, customer.mobileNo) && Objects.equals(address, customer.address) && Objects.equals(bank, customer.bank) && Objects.equals(bookingList, customer.bookingList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, mobileNo, IsActive, address, bank, bookingList);
    }
}
