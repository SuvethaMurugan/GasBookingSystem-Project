package com.companyname.GasBookingSystem.customer;

import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderRepository;
import com.companyname.GasBookingSystem.cylinder.CylinderType;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService{
    @Autowired
    private CylinderRepository cylinderRepository;
    @Override
    public List<Cylinder> getAllCylindersOfMedical(CylinderType type) {
         return this.cylinderRepository.findAllByType(type);
    }
}
