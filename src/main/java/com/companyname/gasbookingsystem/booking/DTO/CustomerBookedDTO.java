package com.companyname.gasbookingsystem.booking.DTO;

import com.companyname.gasbookingsystem.booking.BookingStatusType;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomerBookedDTO {
    private Integer id;
    private Integer cylinderid;
    private Integer bookingid;
    private LocalDate bookingDate;
    private LocalDate deliveryDate;
    private BookingStatusType status;
    private CylinderType cylinderType;
}
