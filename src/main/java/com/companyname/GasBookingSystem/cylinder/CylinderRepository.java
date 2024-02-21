package com.companyname.GasBookingSystem.cylinder;

import com.companyname.GasBookingSystem.cylinder.dto.CylinderGetDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CylinderRepository extends JpaRepository<Cylinder,Integer> {
    List<Cylinder> findAllByType(CylinderType type);
}
