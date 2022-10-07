package com.suhane.bank.repository;

import com.suhane.bank.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountEntity,Integer> {

    AccountEntity findByAccountNumber(String accountNumber);

    @Query(value = "select acc.amount from bank_account acc where acc.acc_number =?1",nativeQuery = true)
    Integer findAccountAmount(String accNumber);
}
