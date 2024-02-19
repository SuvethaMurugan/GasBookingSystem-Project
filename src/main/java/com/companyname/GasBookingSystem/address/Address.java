package com.companyname.GasBookingSystem.address;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Id")
    private Integer id;
    @Column(name="DoorNo")
    private Integer doorNo;
    @Column(name="StreetName")
    private String streetName;
    @Column(name="City")
    private String City;
    @Column(name="PinCode")
    private String pinCode;
}
