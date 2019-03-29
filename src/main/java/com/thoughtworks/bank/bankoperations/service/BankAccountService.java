package com.thoughtworks.bank.bankoperations.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thoughtworks.bank.bankoperations.db.Database;
import com.thoughtworks.bank.bankoperations.model.ResponseMsg;
import com.thoughtworks.bank.bankoperations.model.ServiceRequest;
import com.thoughtworks.bank.bankoperations.model.ServiceResponse;

@Service
public class BankAccountService {
    
    @Autowired Database database;
    
    public ServiceResponse deposit(final ServiceRequest request) {
	ServiceResponse response = new ServiceResponse();
	response.setBalance(database.update(request.getAmount(), com.thoughtworks.bank.bankoperations.model.Service.DEPOSIT));
	
	return response;
    }
    
    public ServiceResponse withdraw(final ServiceRequest request) {
	ServiceResponse response = new ServiceResponse();
	response.setBalance(database.update(request.getAmount(), com.thoughtworks.bank.bankoperations.model.Service.WITHDRAWAL));
	
	return response;
    }
    
    public ServiceResponse balance() {
	ServiceResponse response = new ServiceResponse();
	response.setBalance(database.balance());	
	return response;
    }
    
    public ServiceResponse statement() {
	ServiceResponse response = new ServiceResponse();
	response.setLedger(database.statement());
	return response;
    }
}
