package com.companyname.gasbookingsystem.cylinder;

import com.companyname.gasbookingsystem.booking.Booking;
import com.companyname.gasbookingsystem.booking.BookingRepository;
import com.companyname.gasbookingsystem.cylinder.dto.BookedCylinderDTO;
import com.companyname.gasbookingsystem.cylinder.exception.AddCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.DeleteCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.GetCylinderException;
import com.companyname.gasbookingsystem.cylinder.exception.UpdateCylinderException;
import com.companyname.gasbookingsystem.cylinder.dto.CylinderDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.print.Book;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CylinderServiceImpl implements CylinderService{
    private final CylinderRepository cylinderRepository;
    private final BookingRepository bookingRepository;

    public CylinderServiceImpl(CylinderRepository cylinderRepository, BookingRepository bookingRepository) {
        this.cylinderRepository = cylinderRepository;
        this.bookingRepository = bookingRepository;
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

    @Override
    public List<Cylinder> availableCylinder() {
        List<Cylinder> cylinderList=cylinderRepository.findAll();
        return cylinderList.stream().filter(l->l.getIsActive().equals(true)).toList();
    }

    @Override
    public List<BookedCylinderDTO> BookedCylinder() {
        List<BookedCylinderDTO> list=new ArrayList<>();
        List<Booking> bookingList=this.bookingRepository.findAll();
        for(Booking s:bookingList){
            BookedCylinderDTO bookedCylinderDTO=new BookedCylinderDTO();
            bookedCylinderDTO.setId(s.getId());
            bookedCylinderDTO.setCylinderid(s.getCylinder().getCylinderId());
            bookedCylinderDTO.setCylinderType(s.getCylinder().getType());
            bookedCylinderDTO.setBookingDate(s.getBookingDate());
            bookedCylinderDTO.setDeliveryDate(s.getDeliveryDate());
            bookedCylinderDTO.setCustomerid(s.getCustomer().getId());
            list.add(bookedCylinderDTO);
        }
        return list;
    }

}

