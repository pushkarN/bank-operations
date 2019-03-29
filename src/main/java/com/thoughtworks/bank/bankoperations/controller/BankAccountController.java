package com.thoughtworks.bank.bankoperations.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thoughtworks.bank.bankoperations.model.ServiceRequest;
import com.thoughtworks.bank.bankoperations.model.ServiceResponse;
import com.thoughtworks.bank.bankoperations.service.BankAccountService;

@RestController
public class BankAccountController {
    
    @Autowired
    private BankAccountService bankAccountService;
    
    @PostMapping(value = "/deposit")
    public ResponseEntity<ServiceResponse> deposit(@RequestBody final ServiceRequest request) {
	ServiceResponse response = bankAccountService.deposit(request);
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @PostMapping(value = "/withdraw")
    public ResponseEntity<ServiceResponse> withdraw(@RequestBody final ServiceRequest request) {
	ServiceResponse response = bankAccountService.withdraw(request);
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/balance")
    public ResponseEntity<ServiceResponse> balance() {
	ServiceResponse response = bankAccountService.balance();
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }
    
    @GetMapping(value = "/statement")
    public ResponseEntity<ServiceResponse> statement() {
	ServiceResponse response = bankAccountService.statement();
	
	return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
