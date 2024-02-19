package com.companyname.GasBookingSystem.address;

import org.springframework.boot.autoconfigure.amqp.RabbitConnectionDetails;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address,Integer> {
}
