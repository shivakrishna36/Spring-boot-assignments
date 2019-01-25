package com.capgemini.app.accounts.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.app.accounts.bankaccounts.Account;
import com.capgemini.app.accounts.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository repo;

	@Override
	public List<Account> getAllAccounts() {
		return repo.findAll();
	}

	@Override
	public Optional<Account> getById(int accountNumber) {
		return repo.findById(accountNumber);
	}

	@Override
	public void update(Account account) {
		 repo.save(account);
	}

	@Override
	public Optional<Account> getAccount(int accountNumber) {
		return repo.findById(accountNumber);
	}

	@Override
	public void deleteAccount(int accountNumber) {
		repo.deleteById(accountNumber);
	}

	@Override
	public void updateBalance(double balance) {
		
	}

	

	
	
	
}
