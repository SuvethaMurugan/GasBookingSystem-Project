package com.companyname.gasbookingsystem.customer.dto;

import com.companyname.gasbookingsystem.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
//import address.*;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class registerUserDTO {

        private String userName;
        private String password;
        private String email;
        private String mobileNo;
        private Address address;

}
