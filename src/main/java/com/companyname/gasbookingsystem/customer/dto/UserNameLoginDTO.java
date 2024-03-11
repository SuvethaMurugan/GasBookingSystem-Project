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
public class UserNameLoginDTO {
        @NotNull(message = "UserName should not be null")
        private String userName;
        @NotNull(message = "Password should not be null")
        private String password;
}
