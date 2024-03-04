package com.companyname.gasbookingsystem.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class BankLinkingDTO {
    private Integer customerId;
    private Integer bankId;
    private String password;
}
