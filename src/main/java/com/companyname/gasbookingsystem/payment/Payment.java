package com.companyname.gasbookingsystem.payment;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Payment_Id")
    private Integer paymentId;
    @Column(name="Payment_type")
    private String paymentType;
    @Column(name="Payment_Date")
    private LocalDate paymentDate;
    @Column(name="Amount")
    private Double paymentAmount;
    @Enumerated(EnumType.STRING)
    @Column(name="Payment_Status")
    private PaymentStatusType paymentStatus;
}
