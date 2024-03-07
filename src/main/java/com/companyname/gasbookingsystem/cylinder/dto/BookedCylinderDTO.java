package com.companyname.gasbookingsystem.cylinder.dto;

import com.companyname.gasbookingsystem.booking.BookingStatusType;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class BookedCylinderDTO {
    private Integer id;

    private LocalDate bookingDate;
    private Integer customerid;
    private LocalDate deliveryDate;
    private BookingStatusType status;
    private Integer cylinderid;
    private CylinderType cylinderType;
}
