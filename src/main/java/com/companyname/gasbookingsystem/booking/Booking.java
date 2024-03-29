package com.companyname.gasbookingsystem.booking;

import com.companyname.gasbookingsystem.customer.Customer;
import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.payment.Payment;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class Booking {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column(name="Booking_Date")
    private LocalDate bookingDate;
    @Column(name="Delivery_Date")
    private LocalDate deliveryDate;
    @Column(name="Status")
    @Enumerated(EnumType.STRING)
    private BookingStatusType status;
    @OneToOne
    private Payment payment;
    @ManyToOne
    @JsonIgnore
    private Cylinder cylinder;
    @ManyToOne
    @JsonIgnore
    private Customer customer;
}
