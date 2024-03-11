package com.companyname.gasbookingsystem.cylinder;


import com.companyname.gasbookingsystem.booking.BookingService;
import com.companyname.gasbookingsystem.cylinder.dto.BookedCylinderDTO;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("admin")
@CrossOrigin(origins = "http://localhost:4200")
public class CylinderController {

    private final CylinderService cylinderService;
    private final BookingService bookingService;

    public CylinderController(CylinderService cylinderService, BookingService bookingService) {
        this.cylinderService = cylinderService;
        this.bookingService = bookingService;
    }


    @PostMapping("Cylinder")
    public Cylinder addCylinder(@RequestBody CylinderDTO cylinderDTO) throws AddCylinderException {
        return this.cylinderService.addCylinder(cylinderDTO);
    }

    @GetMapping("Cylinder/{id}")
    public Cylinder getCylinderById(@PathVariable("id") Integer cylinderId) throws GetCylinderException {
        return this.cylinderService.getCylinderById(cylinderId);
    }
    @GetMapping("available")
    public List<Cylinder> availableCylinder() throws GetCylinderException {
        return this.cylinderService.availableCylinder();
    }


    @PutMapping("Cylinder")
    public Cylinder updateCylinder(@RequestBody Cylinder cylinder) throws UpdateCylinderException {
        return this.cylinderService.updateCylinder(cylinder);

    }

    @PatchMapping("Cylinder/{id}")
    public Cylinder deleteCylinder(@PathVariable("id") Integer cylinderId) throws DeleteCylinderException {
        return this.cylinderService.deleteCylinder(cylinderId);
    }
    @GetMapping("available/booked")
    public List<BookedCylinderDTO> BookedCylinder() throws GetCylinderException {
        return this.cylinderService.BookedCylinder();
    }


}



