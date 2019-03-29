package com.thoughtworks.bank.bankoperations;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.thoughtworks.bank.bankoperations.db.Database;
import com.thoughtworks.bank.bankoperations.db.InMemoryDatabase;
import com.thoughtworks.bank.bankoperations.model.Service;

public class Test {

    public static void main(String[] args) throws InterruptedException, ExecutionException {
	Database database = new InMemoryDatabase();
	
	ExecutorService executorService = Executors.newFixedThreadPool(100);

	Set<Callable<Double>> callables = new HashSet<Callable<Double>>();

	for (int i = 0; i < 7000; i++) {
	    if (i % 2 == 0) {
		callables.add(() -> {
		    return database.update(50.0, Service.DEPOSIT);
		});
	    } else {
		callables.add(() -> {
		    return database.update(50.0, Service.WITHDRAWAL);
		});
	    }
	}

	List<Future<Double>> futures = executorService.invokeAll(callables);

	/*futures.stream().map(a -> {
	    try {
		return a.get();
	    } catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
		return -1;
	    }
	}).forEach(System.out::println); */
	
	futures.stream().forEach(a -> {
	    try {
		a.get();
	    } catch (InterruptedException | ExecutionException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	});
	
	System.out.println("Final balance - " + database.balance());
	//System.out.println("Ledger - " + database.statement());
	
	executorService.shutdown();
	
    }

    private static int sum(int i, int j) {
	System.out.println(i);
	return i + j;
    }
}
