package com.companyname.gasbookingsystem.payment.dto;

import jakarta.validation.constraints.Min;
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
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private Integer customerId;
    @NotNull(message = "Booking Id should not be null")
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private Integer bookingId;
    private String password;

}
