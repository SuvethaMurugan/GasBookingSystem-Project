package com.companyname.gasbookingsystem.booking.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RefillCylinderDTO {
    private Integer customerId;
    private Integer cylinderId;
    private Integer bookingId;
}
