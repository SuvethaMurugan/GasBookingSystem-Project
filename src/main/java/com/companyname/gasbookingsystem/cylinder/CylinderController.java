package com.companyname.gasbookingsystem.cylinder;


import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("admin")
public class CylinderController {

    private final CylinderService cylinderService;

    public CylinderController(CylinderService cylinderService) {
        this.cylinderService = cylinderService;
    }

    @PostMapping("Cylinder")
    public Cylinder addCylinder(@RequestBody CylinderDTO cylinderDTO) throws AddCylinderException {
        return this.cylinderService.addCylinder(cylinderDTO);
    }

    @GetMapping("Cylinder/{id}")
    public Cylinder getCylinderById(@PathVariable("id") Integer cylinderId) throws GetCylinderException {
        return this.cylinderService.getCylinderById(cylinderId);
    }

    @PutMapping("Cylinder")
    public Cylinder updateCylinder(@RequestBody Cylinder cylinder) throws UpdateCylinderException {
        return this.cylinderService.updateCylinder(cylinder);

    }

    @PatchMapping("Cylinder/{id}")
    public Cylinder deleteCylinder(@PathVariable("id") Integer cylinderId) throws DeleteCylinderException {
        return this.cylinderService.deleteCylinder(cylinderId);
    }

}



