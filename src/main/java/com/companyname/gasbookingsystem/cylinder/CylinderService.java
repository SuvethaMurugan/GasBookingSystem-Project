package com.companyname.gasbookingsystem.cylinder;

import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.deleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.getCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.updateCylinderException;

public interface CylinderService {
    public Cylinder addCylinder(CylinderDTO cylinder) throws AddCylinderException;

    public Cylinder getCylinderById(Integer cylinderId) throws getCylinderException;

    public Cylinder updateCylinder(Cylinder cylinder) throws updateCylinderException;

    public Cylinder deleteCylinder(Integer cylinderId) throws deleteCylinderException;
}
