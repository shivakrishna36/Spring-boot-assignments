package com.capgemini.app.accounts.service;

import java.util.List;
import java.util.Optional;

import com.capgemini.app.accounts.bankaccounts.Account;

public interface AccountService {
	List<Account> getAllAccounts();

	Optional<Account> getById(int accountNumber);

	void update(Account account);

	Optional<Account> getAccount(int accountNumber);

	void deleteAccount(int accountNumber);

	void updateBalance(double balance);



	
}
