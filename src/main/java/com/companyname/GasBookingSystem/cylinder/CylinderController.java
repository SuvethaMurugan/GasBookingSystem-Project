package com.companyname.GasBookingSystem.cylinder;

import com.companyname.GasBookingSystem.cylinder.Cylinder;
import com.companyname.GasBookingSystem.cylinder.CylinderService;
import com.companyname.GasBookingSystem.cylinder.Exception.AddCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.deleteCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.getCylinderException;
import com.companyname.GasBookingSystem.cylinder.Exception.updateCylinderException;
import com.companyname.GasBookingSystem.cylinder.dto.CylinderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("admin")
public class CylinderController {

        @Autowired
        private CylinderService cylinderService;

        @PostMapping("addCylinder")
        public Cylinder addCylinder(@RequestBody CylinderDTO cylinderDTO ) throws AddCylinderException {
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



