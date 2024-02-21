package com.companyname.GasBookingSystem.customer.dto;

import com.companyname.GasBookingSystem.address.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDTO {
        private Integer id;
        private String userName;
        private String password;
        private String email;
        private String mobileNo;
        private boolean IsActive;
        private Address address;
}
