package com.companyname.gasbookingsystem.customer.dto;

import com.companyname.gasbookingsystem.address.Address;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserDTO {
        @NotNull(message = "UserName should not be null")
        private String userName;
        @NotNull(message = "Password should not be null")
        private String password;
        @NotNull(message = "Email should not be null")
        private String email;
        @NotNull(message = "Mobile num should not be null")
        private String mobileNo;
        @NotNull(message = "Address should not be null")
        private Address address;

}
