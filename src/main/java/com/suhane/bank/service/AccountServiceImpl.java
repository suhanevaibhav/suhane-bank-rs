package com.suhane.bank.service;

import com.suhane.bank.dto.Account;
import com.suhane.bank.entity.AccountEntity;
import com.suhane.bank.exception.BankServiceException;
import com.suhane.bank.repository.AccountRepository;
import com.suhane.bank.utill.BankUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl {

    private static final Logger log = LogManager.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private BankUtility utility;

    public Account saveAccount(Account account) {
        try {
            AccountEntity entity = utility.convertAccountDtoToEntity(account);
            AccountEntity resEntity = accountRepository.save(entity);
            log.info("save the Account successfully : {} ",resEntity);
            return utility.convertAccountEntityToDto(resEntity);
        } catch (Exception e) {
            log.info("failed to user Account : {} {}",account,e.getMessage());
            throw new BankServiceException(e.getMessage());
        }
    }

    public Account getAccountDetails(String accountNumber) {
        try {
            AccountEntity account = accountRepository.findByAccountNumber(accountNumber);
            log.info("Successfully find the Account details : {}",account);
            return utility.convertAccountEntityToDto(account);
        } catch (Exception e) {
            log.info("Exception in to find the Account details : {} {}",accountNumber,e.getMessage());
            throw new BankServiceException(e.getMessage());
        }
    }

    public Integer getAccountAmount(String accountNumber) {
        try {
            return accountRepository.findAccountAmount(accountNumber);
        } catch (Exception e) {
            log.info("Unable to fetch account balance : {}",e.getMessage());
            throw new BankServiceException(e.getMessage());
        }
    }

    public Account debitAmount(String accNumber, Integer amount) {
        try {
            Integer accBalance = getAccountAmount(accNumber);
            Integer balance = accBalance - amount;
            if (balance >= 0) {

                AccountEntity accountEntity = accountRepository.findByAccountNumber(accNumber);
                accountEntity.setAmount(balance);
                AccountEntity resEntity = accountRepository.save(accountEntity);
                return utility.convertAccountEntityToDto(resEntity);
            } else {
                log.info("Account balance is insufficient");
                throw new BankServiceException("Account balance is insufficient");
            }
        } catch (Exception e) {
            log.info("Exception in debit Amount method : {}",e.getMessage());
            throw new BankServiceException(e.getMessage());
        }
    }

    public Account creditAmount(String accNumber, Integer amount) {
        try {
            AccountEntity entity = accountRepository.findByAccountNumber(accNumber);
            Integer balance = entity.getAmount() + amount;
            entity.setAmount(balance);
            AccountEntity resEntity = accountRepository.save(entity);
            log.info("Amount is Credited successfully ");
            return utility.convertAccountEntityToDto(resEntity);
        } catch (Exception e) {
            log.info("Amount is not Credited : {}", e.getMessage() );
            throw new BankServiceException(e.getMessage());
        }

    }

}
