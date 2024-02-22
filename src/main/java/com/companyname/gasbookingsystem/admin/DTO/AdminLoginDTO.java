package com.companyname.gasbookingsystem.admin.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdminLoginDTO {
    Integer adminId;
    String emailId;
    String adminName;
    String password;
}

