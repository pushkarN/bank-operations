package com.thoughtworks.bank.bankoperations.model;

import java.io.Serializable;

import lombok.Data;

public class ServiceRequest implements Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 1848915890928866854L;
    
    private double amount;

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "ServiceRequest{" +
                "amount=" + amount +
                '}';
    }
}
