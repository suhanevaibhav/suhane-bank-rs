package com.suhane.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "user_info")
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "address")
    private String address;
    @Column(name = "father_name")
    private String fatherName;
    @Column(name = "aadhar_card")
    private String aadharCard;
    @Column(name = "mobile")
    private String mob;
    @Column(name = "pan_card")
    private String panCard;
    @Column(name = "dob")
    private Date dob;
    @Column(name = "account_number")
    private String accountNumber;
}
