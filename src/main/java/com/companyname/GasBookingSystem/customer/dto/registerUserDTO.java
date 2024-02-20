package com.companyname.GasBookingSystem.customer.dto;

import com.companyname.GasBookingSystem.address.Address;
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
