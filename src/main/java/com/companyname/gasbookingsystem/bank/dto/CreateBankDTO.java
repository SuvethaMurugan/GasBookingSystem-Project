package com.companyname.gasbookingsystem.bank.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBankDTO {
    private String username;
    private String password;
    private Double balance;

}
