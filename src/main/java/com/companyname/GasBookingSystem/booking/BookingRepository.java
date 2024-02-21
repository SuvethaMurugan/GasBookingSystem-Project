package com.companyname.GasBookingSystem.booking;

import com.sun.source.tree.LambdaExpressionTree;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface BookingRepository extends JpaRepository<Booking,Integer> {
    List<Booking> findAllByDeliveryDate(LocalDate date);
}
