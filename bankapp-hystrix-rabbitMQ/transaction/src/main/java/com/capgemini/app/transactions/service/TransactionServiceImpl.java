package com.capgemini.app.transactions.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.app.transactions.entity.Transaction;
import com.capgemini.app.transactions.entity.TransactionType;
import com.capgemini.app.transactions.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	TransactionRepository repository;
	
	public double deposit(int accountNumber,double amount,double currentBalance,String transactionDetails)
	{
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance = currentBalance + amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDetails(transactionDetails);
		transaction.setTransactionType(TransactionType.DEPOSIT);
		transaction.setTransactionDate(LocalDateTime.now());
		repository.save(transaction);
		return currentBalance;
	}

	@Override
	public List<Transaction> getStatement() {
		return repository.findAll();
	}

	@Override
	public double withdraw(Integer accountNumber, Double amount, double currentBalance, String transactionDetails) {
		Transaction transaction = new Transaction();
		transaction.setAccountNumber(accountNumber);
		transaction.setAmount(amount);
		currentBalance = currentBalance - amount;
		transaction.setCurrentBalance(currentBalance);
		transaction.setTransactionDetails(transactionDetails);
		transaction.setTransactionType(TransactionType.WITHDRAW);
		transaction.setTransactionDate(LocalDateTime.now());
		repository.save(transaction);
		return currentBalance;
	}
	
}
