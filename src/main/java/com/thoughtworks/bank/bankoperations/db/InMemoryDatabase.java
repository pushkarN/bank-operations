package com.thoughtworks.bank.bankoperations.db;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.stereotype.Component;

import com.thoughtworks.bank.bankoperations.model.Operation;
import com.thoughtworks.bank.bankoperations.model.Service;

@Component
public class InMemoryDatabase implements Database {

    final private ReadWriteLock lock = new ReentrantReadWriteLock();
    final private Lock readLock = lock.readLock();
    final private Lock writeLock = lock.writeLock();

    private double accountBalance = 0;

    private List<Operation> ledger = new ArrayList<Operation>();

    @Override
    public double update(double amount, final Service service) {
	
	//System.out.println(Thread.currentThread().getName() + " - " + service.name());

	writeLock.lock();
	try {
	    // update balance
	    if (service == Service.DEPOSIT) {
		accountBalance += amount;
	    } else {
		accountBalance -= amount;
	    }

	    // Add entry to statement
	    Operation operation = new Operation(service, amount);
	    ledger.add(operation);

	} finally {
	    writeLock.unlock();
	}

	readLock.lock();
	
	try {
	    // reconcile
	    // TODO: Write for loop
	    double result = 0.0;
	    for (Operation ops : ledger) {
		if (ops.getService() == Service.DEPOSIT) {
		    result += ops.getAmount();
		} else {
		    result -= ops.getAmount();
		}
	    }
	    
	    if (result != accountBalance) {
		System.out.println("Reconcile failed!");
	    }
	    
	    return accountBalance;
	} finally {
	    readLock.unlock();
	}
    }

    @Override
    public double balance() {
	readLock.lock();
	
	try {
	    return accountBalance;
	} finally {
	    readLock.unlock();
	}
    }

    @Override
    public List<Operation> statement() {
	readLock.lock();
	
	try {
	    return ledger;
	} finally {
	    readLock.unlock();
	}
    }
}
