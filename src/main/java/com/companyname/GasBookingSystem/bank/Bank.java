package com.companyname.GasBookingSystem.bank;

import jakarta.persistence.*;
import lombok.*;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Bank_Id")
    private Integer bankId;
    @Column(name="Account_No")
    private String accountNo;
    @Column(name="Bank_Name")
    private String bankName;
    @Column(name="Branch")
    private String branch;
    @Column(name="Balance")
    private Double balance;
    @Column(name="IsActive")
    private Boolean isActive;

}
