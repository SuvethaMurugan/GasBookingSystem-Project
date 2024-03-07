package com.companyname.gasbookingsystem;

import com.companyname.gasbookingsystem.cylinder.Cylinder;
import com.companyname.gasbookingsystem.cylinder.CylinderService;
import com.companyname.gasbookingsystem.cylinder.CylinderServiceImpl;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderAddDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

@SpringBootTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)

public class CylinderTest {
    @Autowired
    private CylinderService cylinderService;
    Cylinder cylinder;

}
