package com.capgemini.app.transactions.service;

import java.util.List;

import com.capgemini.app.transactions.entity.Transaction;

public interface TransactionService {

	double deposit(int accountNumber,double amount,double currentBalance,String transactionDetails);

	List<Transaction> getStatement();

	double withdraw(Integer accountNumber, Double amount, double currentBalance, String transactionDetails);
}