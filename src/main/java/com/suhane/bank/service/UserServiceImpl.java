package com.suhane.bank.service;

import com.suhane.bank.dto.User;
import com.suhane.bank.entity.UserEntity;
import com.suhane.bank.exception.BankServiceException;
import com.suhane.bank.repository.UserRepository;
import com.suhane.bank.utill.BankUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl {

    private static final Logger log = LogManager.getLogger(UserServiceImpl.class);
    @Autowired
    private UserRepository repository;

    @Autowired
    private BankUtility utility;

    public User saveUser(User user) {
        try {
            UserEntity userEntity = utility.convertDtoToEntity(user);
            UserEntity entity;
            userEntity.setAccountNumber(utility.genrateAccountNumber());
            try {
                entity = repository.save(userEntity);
            } catch (Exception e) {

                if (e.getMessage().contains("user_info_account_number_key")) {
                    userEntity.setAccountNumber(utility.genrateAccountNumber());
                    entity = repository.save(userEntity);
                    log.info("User save successfully : {}",entity);
                } else {
                    log.info("user already enrolled :{}",user.getName());
                    throw new BankServiceException("user already enrolled");
                }
            }

            return utility.covertEntityToDto(entity);
        } catch (Exception e) {
            log.info("Failed to enrolled user : {} {}",user,e.getMessage());
            throw new BankServiceException(e.getMessage());
        }
    }

    public User findUserByAadhar(String aadhar) {
        try {
            UserEntity userEntity = repository.findByAadharCard(aadhar);
            log.info("Successfully find the User by Aadhar number : {}", userEntity);
            return utility.covertEntityToDto(userEntity);
        }catch (Exception e) {
            log.info("Enable to find user by Aadhaar number : {}",aadhar);
            throw new BankServiceException(e.getMessage());
        }
    }

}
