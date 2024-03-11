package com.companyname.gasbookingsystem.booking.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BookingDTO {
        @NotNull(message = "Cylinder ID should not be null")
        private Integer customerId;
        @NotNull(message = "Customer ID should not be null")
        private Integer cylinderId;
}

