package com.companyname.gasbookingsystem.payment.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankLinkingDTO {
    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private Integer customerId;
    @NotNull
    @Min(value = 1, message = "Value must be greater than or equal to 1")
    private Integer bankId;
    @NotNull
    private String password;
}
