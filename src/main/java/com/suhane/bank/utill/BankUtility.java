package com.suhane.bank.utill;

import com.suhane.bank.dto.Account;
import com.suhane.bank.dto.User;
import com.suhane.bank.entity.AccountEntity;
import com.suhane.bank.entity.UserEntity;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
@Component
public class BankUtility {
    SimpleDateFormat dateformat = new SimpleDateFormat("dd/MM/yyyy");

    public User covertEntityToDto(UserEntity entity) {
        User user = new User();
        user.setName(entity.getName());
        user.setLastName(entity.getLastName());
        user.setFatherName(entity.getFatherName());
        user.setDob(convertDateToString(entity.getDob()));
        user.setMob(entity.getMob());
        user.setAddress(entity.getAddress());
        user.setAadharCard(entity.getAadharCard());
        user.setPanCard(entity.getPanCard());
        user.setAccountNumber(entity.getAccountNumber());
        return user;
    }
    public UserEntity convertDtoToEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setName(user.getName());
        entity.setLastName(user.getLastName());
        entity.setFatherName(user.getFatherName());
        entity.setAddress(user.getAddress());
        entity.setDob(convertStringToDate(user.getDob()));
        entity.setMob(user.getMob());
        entity.setAadharCard(user.getAadharCard());
        entity.setPanCard(user.getPanCard());
        return entity;
    }
    public Date convertStringToDate(String strDate) {

        Date date = new Date();
        try {
            date = dateformat.parse(strDate);
        } catch (Exception e) {
            System.out.println(e);
        }
        return date;
    }
    public String convertDateToString(Date date) {

        return dateformat.format(date);
    }

    public String genrateAccountNumber() {
        int length = 10;
        boolean useLetters = false;
        boolean useNumbers = true;
        String accountNumber = "SUHANE" + RandomStringUtils.random(length, useLetters, useNumbers);
        return accountNumber;
    }

    public Account convertAccountEntityToDto(AccountEntity accountEntity) {
        Account account = new Account();
        account.setAccountNumber(accountEntity.getAccountNumber());
        account.setAccountHolderName(accountEntity.getAccountHolderName());
        account.setAccountLoginName(accountEntity.getAccountLoginName());
        account.setAccountPwd(accountEntity.getAccountPwd());
        account.setAmount(accountEntity.getAmount());
        return account;
    }

    public AccountEntity convertAccountDtoToEntity(Account account) {
        AccountEntity entity = new AccountEntity();
        entity.setAccountNumber(account.getAccountNumber());
        entity.setAccountHolderName(account.getAccountHolderName());
        entity.setAccountLoginName(account.getAccountLoginName());
        entity.setAccountPwd(account.getAccountPwd());
        entity.setAmount(account.getAmount());
        return entity;
    }
}
