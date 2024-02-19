package com.companyname.GasBookingSystem.payment.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PaymentUpdateDTO {
    @NotNull(message = "Customer Id should not be null")
    private Integer customerId;
    @NotNull(message = "Booking Id should not be null")
    private Integer bookingId;
}
