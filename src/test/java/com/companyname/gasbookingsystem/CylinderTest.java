package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderService;
import com.companyname.gasbookingsystem.cylinder.CylinderServiceImpl;
import com.companyname.gasbookingsystem.cylinder.CylinderType;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderAddDTO;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CylinderTest {
    @Autowired
    private CylinderService cylinderService;
    Cylinder cylinder;
    @Test
    public void addCylinderTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertNotNull(cylinderService.addCylinder(cylinderDTO));
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    @DisplayName("Cylinder WeightNull Test")
    public void CylinderWeightNullTest()
    {
        CylinderDTO cylinderDTO = new CylinderDTO(CylinderType.Medical, null, 500.0);
        Assertions.assertThrows(AddCylinderException.class, () -> cylinderService.addCylinder(cylinderDTO));
    }
    @Test
    @DisplayName("Cylinder Price Test")
    public void CylinderPriceTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            Assertions.assertNotNull(cylinderService.addCylinder(cylinderDTO));
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Cylinder PriceNull Test")
    public void CylinderPriceNullTest()
    {
        try {
            CylinderDTO cylinderDTO = new CylinderDTO(CylinderType.Medical, 5.0, null);
            Assertions.assertThrows(AddCylinderException.class,()->cylinderService.addCylinder(cylinderDTO));
        }
        catch(Exception e)
        {
            Assertions.assertEquals("Cylinder price cant be null",e.getMessage());
        }
    }
    @Test
    @DisplayName("GetCylinderById Test")
    public void getCylinderByIdTest()
    {

       CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);

        try {

            cylinder= cylinderService.addCylinder(cylinderDTO);
            Assertions.assertNotNull(cylinderService.getCylinderById(cylinder.getCylinderId()));
        }
        catch(GetCylinderException|AddCylinderException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("GetCylinderBYID Null Test")
    public void getCylinderByIdNullTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try
        {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(GetCylinderException.class,()->cylinderService.getCylinderById(null));

        } catch (AddCylinderException e)
        {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Update CylinderByWeight Test")
    public void updateCylinderByWeightTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try
        {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(5.0,cylinderService.updateCylinder(cylinder).getWeight());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (UpdateCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("UpdateCylinderWeight less than 5kg Test")
    public void updateCylinderWeightLessThan5kgTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,4.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(UpdateCylinderException.class,
                    ()->cylinderService.updateCylinder(cylinder).getWeight());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("Update CylinderPrice Test")
    public void updateCylinderPriceTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(500.0,cylinderService.updateCylinder(cylinder).getPrice());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (UpdateCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("UpdateCylinder Price less than 500 Test")
    public void updateCylinderPriceLessThan500Test()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,600.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(UpdateCylinderException.class,
                    ()->cylinderService.updateCylinder(cylinder).getPrice());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }

    }
    @Test
    @DisplayName("DeleteCylinder Test")
    public void deleteCylinderTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertEquals(1,cylinderService.deleteCylinder(cylinder.getCylinderId()).getCylinderId());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        } catch (DeleteCylinderException e) {
            throw new RuntimeException(e);
        }
    }
    @Test
    @DisplayName("DeleteCylinderId Not found Test")
    public void deleteCylinderIdNotFoundTest()
    {
        CylinderDTO cylinderDTO=new CylinderDTO(CylinderType.Medical,5.0,500.0);
        try {
            cylinder=cylinderService.addCylinder(cylinderDTO);
            Assertions.assertThrows(DeleteCylinderException.class,
                    ()->cylinderService.deleteCylinder(2).getCylinderId());
        } catch (AddCylinderException e) {
            throw new RuntimeException(e);
        }

    }




}
