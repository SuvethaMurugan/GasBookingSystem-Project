package com.companyname.GasBookingSystem.payment.dto;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PaymentDTO {
    private Integer paymentId;
    private String paymentType;
    private LocalDate paymentDate;
    private Double paymentAmount;
    private String paymentStatus;
}
