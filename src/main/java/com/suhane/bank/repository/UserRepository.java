package com.suhane.bank.repository;

import com.suhane.bank.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity,Integer> {

    UserEntity findByAadharCard(String aadharCard);
}
