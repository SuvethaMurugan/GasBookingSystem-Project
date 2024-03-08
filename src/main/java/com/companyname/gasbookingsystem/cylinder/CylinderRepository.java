package com.companyname.gasbookingsystem.cylinder;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CylinderRepository extends JpaRepository<Cylinder,Integer> {
    List<Cylinder> findAllByType(CylinderType type);

}
