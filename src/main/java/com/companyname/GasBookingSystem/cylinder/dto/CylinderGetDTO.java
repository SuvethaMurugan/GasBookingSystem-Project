package com.companyname.GasBookingSystem.cylinder.dto;

import com.companyname.GasBookingSystem.cylinder.CylinderType;
import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CylinderGetDTO {
    private CylinderType type;
    private Double weight;
    private Double price;
}
