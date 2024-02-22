package com.companyname.gasbookingsystem.cylinder;


import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.deleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.getCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.updateCylinderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class CylinderController {

    @Autowired
    private CylinderService cylinderService;

    @PostMapping("addCylinder")
    public Cylinder addCylinder(@RequestBody CylinderDTO cylinderDTO) throws AddCylinderException {
        return this.cylinderService.addCylinder(cylinderDTO);
    }

    @GetMapping("getCylinder/{id}")
    public Cylinder getCylinderById(@PathVariable("id") Integer cylinderId) throws getCylinderException {
        return this.cylinderService.getCylinderById(cylinderId);
    }

    @PutMapping("updateCylinder")
    public Cylinder updateCylinder(@RequestBody Cylinder cylinder) throws updateCylinderException {
        return this.cylinderService.updateCylinder(cylinder);

    }

    @PatchMapping("deletecylinder/{id}")
    public Cylinder deleteCylinder(@PathVariable("id") Integer cylinderId) throws deleteCylinderException {
        return this.cylinderService.deleteCylinder(cylinderId);
    }

}



