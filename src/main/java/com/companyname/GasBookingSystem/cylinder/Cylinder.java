package com.companyname.GasBookingSystem.cylinder;

import jakarta.persistence.*;
import lombok.*;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Cylinder {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="CylinderId")
    private Integer CylinderId;
    @Column(name="Type")
    @Enumerated(EnumType.STRING)
    private CylinderType type;
    @Column(name="Weight")
    private Double weight;
    @Column(name="Price")
    private Double price;
    @Column(name="IsActive")
    private Boolean isActive;

}
