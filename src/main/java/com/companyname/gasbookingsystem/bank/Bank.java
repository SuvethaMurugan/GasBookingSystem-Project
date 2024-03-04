package com.companyname.gasbookingsystem.bank;

import com.companyname.gasbookingsystem.customer.Customer;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class Bank {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="Bank_Id")
    private Integer bankId;
    @Column(name="username")
    private String username;
    @Column(name="Password")
    private String password;
    @Column(name="Bank_Name")
    private final String bankName="SBI";
    @Column(name="Branch")
    private final String branch="Chennai";
    @Column(name="Balance")
    private Double balance;
    @Column(name="IsActive")
    private final Boolean isActive=true;
    @OneToOne
    @JsonIgnore
    private Customer customer;
}
