package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.address.Address;
import com.companyname.GasBookingSystem.bank.Bank;

import com.companyname.GasBookingSystem.booking.Booking;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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
    private String mobileNo;
    @Column(name="IsActive")
    private boolean IsActive;
    @OneToOne
    private Address address;
    @OneToOne
    private Bank bank;
    @OneToMany
    private List<Booking> bookingList;
}
