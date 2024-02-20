package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;

import java.util.List;

public interface CustomerService {
    List<Cylinder> getAllCylindersOfMedical(CylinderType type);
}
