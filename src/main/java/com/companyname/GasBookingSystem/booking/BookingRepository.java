package com.companyname.GasBookingSystem.booking;

import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.data.jpa.repository.JpaRepository;

 

import org.springframework.stereotype.Repository;



  

  
import java.time.LocalDate;
import java.util.List;
@Repository
public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findAllByDeliveryDate(LocalDate date);

}

