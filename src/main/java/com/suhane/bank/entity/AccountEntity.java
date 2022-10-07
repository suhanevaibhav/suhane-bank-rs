package com.suhane.bank.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "bank_account")
public class AccountEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "acc_id")
    private Integer accountId;
    @Column(name = "acc_number")
    private String accountNumber;
    @Column(name = "acc_holder_name")
    private String accountHolderName;
    @Column(name = "acc_login_name")
    private String accountLoginName;
    @Column(name = "pwd")
    private String accountPwd;
    @Column(name = "amount")
    private Integer amount;
}
