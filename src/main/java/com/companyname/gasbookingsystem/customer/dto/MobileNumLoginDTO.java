package com.companyname.gasbookingsystem.customer.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MobileNumLoginDTO {
    @NotNull(message = "Mobile num should not be null")
    private String mobileNo;
    @NotNull(message = "Passsword should not be null")
    private String password;
}

