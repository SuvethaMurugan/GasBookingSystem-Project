package com.companyname.GasBookingSystem.bank.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BankUpdateDTO {
    @NotNull(message = "Customer Id should not be null")
    private Integer customerId;
    @NotNull(message = "AccountNo Id should not be null")
    private String AccountNo;
    @NotNull(message = "Balance should not be null")
    private Double balance;
    @NotNull(message = "Bank Name should not be null")
    private String bankName;
    @NotNull(message = "Branch should not be null")
    private String branch;
}
