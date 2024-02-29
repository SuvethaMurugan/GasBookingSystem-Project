package com.companyname.gasbookingsystem.cylinder;

import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CylinderServiceImpl implements CylinderService{
    private final CylinderRepository cylinderRepository;

    public CylinderServiceImpl(CylinderRepository cylinderRepository) {
        this.cylinderRepository = cylinderRepository;
    }

    @Override
    public Cylinder addCylinder(CylinderDTO cylinder) throws AddCylinderException {
        if(cylinder==null)
        {
            throw new AddCylinderException("cylinder cant be null");
        }
        Cylinder cylinder1=new Cylinder();
        cylinder1.setType(cylinder.getType());
        cylinder1.setPrice(cylinder.getPrice());
        cylinder1.setWeight(cylinder.getWeight());
        cylinder1.setIsActive(Boolean.TRUE);
        return this.cylinderRepository.save(cylinder1);
    }

    @Override
    public Cylinder getCylinderById(Integer cylinderId) throws  GetCylinderException {
        if(cylinderId==null)
        {
            throw new GetCylinderException("cylinderId cant be null");
        }
        Optional<Cylinder> cylinder=this.cylinderRepository.findById(cylinderId);
        if(cylinder.isEmpty())
        {
            throw new GetCylinderException("cylinder id cant be found");
        }
        return cylinder.get();
    }

    @Override
    public Cylinder updateCylinder(Cylinder cylinder) throws UpdateCylinderException {
        if(cylinder==null)
        {
            throw new UpdateCylinderException("cylinder cant be null");
        }
        return cylinderRepository.save(cylinder);
    }

    @Override
    public Cylinder deleteCylinder(Integer cylinderId) throws DeleteCylinderException {
        Optional<Cylinder> cylinder=this.cylinderRepository.findById(cylinderId);
        if(cylinder.isEmpty()) {
            throw new DeleteCylinderException("Cylinder id cant be found");
        }
        Cylinder cylinder1=cylinder.get();
        cylinder1.setIsActive(Boolean.FALSE);
        return this.cylinderRepository.save(cylinder1);
    }

}

