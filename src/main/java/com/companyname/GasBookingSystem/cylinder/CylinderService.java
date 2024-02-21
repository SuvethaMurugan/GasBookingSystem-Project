package com.companyname.GasBookingSystem.cylinder;

import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.deleteCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.getCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.updateCylinderException;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderDTO;

public interface CylinderService {
    public Cylinder addCylinder(CylinderDTO cylinder) throws AddCylinderException;
    public Cylinder getCylinderById(Integer cylinderId) throws getCylinderException;
    public Cylinder updateCylinder(Cylinder cylinder ) throws updateCylinderException;
    public Cylinder deleteCylinder(Integer cylinderId) throws deleteCylinderException;
}
