package com.companyname.GasBookingSystem.booking;

import com.companyname.GasBookingSystem.customer.Customer;
import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.payment.Payment;
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
    @OneToOne
    private Cylinder cylinder;
    @OneToOne
    @JsonIgnore
    private Customer customer;
}
