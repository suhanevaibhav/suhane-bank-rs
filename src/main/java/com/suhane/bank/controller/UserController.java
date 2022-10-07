package com.suhane.bank.controller;

import com.suhane.bank.dto.User;
import com.suhane.bank.service.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/bank/user")
public class UserController {

    private static final Logger log = LogManager.getLogger(UserController.class);
    @Autowired
    private UserServiceImpl service;

    @PostMapping(value = "/save")
    public ResponseEntity<Object> saveUser(@RequestBody User user) {
        try {
            User resUser = service.saveUser(user);
            log.info("User save successfully : {}",user);
            return new ResponseEntity(resUser, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Failed to save user : {} ",user);
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }
    @GetMapping(value = "/aadhar")
    public ResponseEntity<Object> findUserByAadhar(@RequestParam("aadhar") String aadhar) {

        try {
            User user = service.findUserByAadhar(aadhar);
           log.info("successfully find the user by Aadhar card number:{}",aadhar);
           return new ResponseEntity(user, HttpStatus.OK);
        } catch (Exception e) {
            log.info("Unable to fetch user details by Aadhar number : {}",aadhar);
            return new ResponseEntity(e.getMessage(),HttpStatus.EXPECTATION_FAILED);
        }
    }

}
