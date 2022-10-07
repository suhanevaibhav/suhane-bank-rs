package com.suhane.bank.controller;

import com.suhane.bank.dto.Account;
import com.suhane.bank.exception.BankServiceException;
import com.suhane.bank.service.AccountServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bank/account")
public class AccountController {

    private static final Logger logger = LogManager.getLogger(AccountController.class);
    @Autowired
    private AccountServiceImpl accountService;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveAccount(@RequestBody Account account) {
        try {
            Account saveAccount = accountService.saveAccount(account);
            logger.info("save the bank account number :{} successfully", saveAccount.getAccountNumber());
            return new ResponseEntity(saveAccount, HttpStatus.OK);
        } catch (Exception e) {
             logger.info("failed to save the bank account :{} ", account);
             return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/details")
    public ResponseEntity<Object> getAccountDetails(@RequestParam("accnumber") String accNumber) {
        try {
            Account account = accountService.getAccountDetails(accNumber);
            logger.info("user account details: {}", accNumber);
            return new ResponseEntity(account, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed to Fetch user account details for : {}", accNumber);
            return new ResponseEntity<>(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }

    }

    @GetMapping(value = "/balance")
    public ResponseEntity<Object> getCurrentBalance(@RequestParam("accnumber") String accNum) {
        try {
            Integer balance = accountService.getAccountAmount(accNum);
            logger.info("Successfully find the current balance : {}", balance);
            return new ResponseEntity(balance, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed to find the current balance for  : {}", accNum);
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/debit")
    public ResponseEntity<Account> debitMoney(@RequestParam("accnumber") String accNum
                                                  , @RequestParam("money") Integer amount) {
        try {
            Account account = accountService.debitAmount(accNum, amount);
            logger.info("Successfully debited the amount: {} from : {}",amount, accNum);
            return new ResponseEntity<Account>(account, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed to debited the amount: {} in {} ", amount,accNum);
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping(value = "/credit")
    public ResponseEntity<Object> creditMoney(@RequestParam("accnumber") String accNum
            , @RequestParam("money") Integer amount) {
        try {
            Account account = accountService.creditAmount(accNum, amount);
            logger.info("Successfully Credited the amount: {} in : {}",amount,accNum);
            return new ResponseEntity(account, HttpStatus.OK);
        } catch (Exception e) {
            logger.info("Failed to Credited the amount: {} in : {}",amount,accNum);
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }



}
