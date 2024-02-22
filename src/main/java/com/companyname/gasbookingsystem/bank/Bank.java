package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @OneToOne
    @JsonIgnore
    private Customer customer;
}
