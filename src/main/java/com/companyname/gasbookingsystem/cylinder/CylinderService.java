package com.companyname.gasbookingsystem.cylinder;

import com.companyname.gasbookingsystem.cylinder.dto.BookedCylinderDTO;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;

import java.util.List;

public interface CylinderService {
    public Cylinder addCylinder(CylinderDTO cylinder) throws AddCylinderException;

    public Cylinder getCylinderById(Integer cylinderId) throws GetCylinderException;

    public Cylinder updateCylinder(Cylinder cylinder) throws UpdateCylinderException;

    public Cylinder deleteCylinder(Integer cylinderId) throws DeleteCylinderException;

    List<Cylinder> availableCylinder();

    List<BookedCylinderDTO> BookedCylinder();
}
