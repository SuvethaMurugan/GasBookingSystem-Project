package com.companyname.GasBookingSystem.admin;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Table(name="Admin_Entity")
public class Admin {
    @Id
    @Column(name="Admin_Id")
    private Integer adminId;
    @Column(name="User_Name")
    private String userName;
    @Column(name="Email_ID")
    private String email;
    @Column(name="Password")
    private String password;

}
