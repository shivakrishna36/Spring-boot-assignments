package com.capgemini.app.transactions.resource;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.capgemini.app.transactions.entity.Transaction;
import com.capgemini.app.transactions.sender.Sender;
import com.capgemini.app.transactions.service.TransactionService;

@RestController
@RequestMapping("/transactions")
public class TransactionResource {

	@Autowired
	TransactionService service;
	
	@Autowired
	Sender sender;
	
	@Autowired
	RestTemplate restTemplate;
	
	@PostMapping
	public ResponseEntity<Transaction> deposit(@RequestBody Transaction transaction)
	{
		ResponseEntity<Double> entity = restTemplate.getForEntity("http://account/accounts/"+transaction.getAccountNumber()+"/balance",Double.class);
		double currentBalance = entity.getBody();
		double updatedBalance = service.deposit(
				transaction.getAccountNumber(),transaction.getAmount(),currentBalance, transaction.getTransactionDetails());
		transaction.setCurrentBalance(updatedBalance);
		sender.send(transaction);
		System.out.println("im there");
		//restTemplate.put("http://account/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updatedBalance, null);
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}
	
	@PostMapping("/withdraw")
	public ResponseEntity<Transaction> withdraw(@RequestBody Transaction transaction)
	{
		ResponseEntity<Double> entity = restTemplate.getForEntity("http://account/accounts/"+transaction.getAccountNumber()+"/balance",Double.class);
		double currentBalance = entity.getBody();
		double updatedBalance = service.withdraw(
				transaction.getAccountNumber(),transaction.getAmount(),currentBalance, transaction.getTransactionDetails());
		transaction.setCurrentBalance(updatedBalance);
		sender.send(transaction);
		//restTemplate.put("http://account/accounts/"+transaction.getAccountNumber()+"?currentBalance="+updatedBalance, null);
		return new ResponseEntity<Transaction>(HttpStatus.OK);
	}
	
	@GetMapping("/getstatements")
	public CurrentDataSet getStatement() {
		List<Transaction> transactions = service.getStatement();
		CurrentDataSet dataset = new CurrentDataSet();
		dataset.setTransactions(transactions);
		return dataset;
	}
	
}
