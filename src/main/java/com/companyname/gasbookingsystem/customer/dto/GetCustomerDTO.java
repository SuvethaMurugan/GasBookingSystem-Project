package com.companyname.gasbookingsystem.customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetCustomerDTO {
    @NotNull(message = "Customer Id should not be null")
    private Integer customerId;
}
