package com.thoughtworks.bank.bankoperations.model;

import java.io.Serializable;
import java.util.List;

import lombok.Data;

public class ServiceResponse implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 6480713676848031571L;
        
    private double balance;
    
    private List<Operation> ledger;

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public List<Operation> getLedger() {
        return ledger;
    }

    public void setLedger(List<Operation> ledger) {
        this.ledger = ledger;
    }

    @Override
    public String toString() {
        return "ServiceResponse{" +
                "balance=" + balance +
                ", ledger=" + ledger +
                '}';
    }
}
