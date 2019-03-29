package com.thoughtworks.bank.bankoperations.db;

import java.util.List;

import com.thoughtworks.bank.bankoperations.model.Operation;
import com.thoughtworks.bank.bankoperations.model.Service;

public interface Database {
    public double update(double amount, Service service);
    
    public double balance();
    
    public List<Operation> statement();
}
