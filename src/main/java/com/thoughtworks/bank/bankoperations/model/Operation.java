package com.thoughtworks.bank.bankoperations.model;

import java.io.Serializable;

import lombok.Data;

public class Operation implements Serializable {
    /**
     * 
     */
    private static final long serialVersionUID = 7792313291832001923L;
    
    public Operation(Service service, double amount) {
	this.service = service;
	this.amount = amount;
    }
    
    private Service service;
    
    private double amount;

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Operation{" +
                "service=" + service +
                ", amount=" + amount +
                '}';
    }
}
