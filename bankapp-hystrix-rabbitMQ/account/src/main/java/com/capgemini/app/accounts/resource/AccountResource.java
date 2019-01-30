package com.capgemini.app.accounts.resource;

import java.util.List;
import java.util.Optional;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.app.accounts.bankaccounts.Account;
import com.capgemini.app.accounts.service.AccountService;
import com.capgemini.app.transactions.entity.Transaction;

@RestController
@RequestMapping("/accounts")
public class AccountResource {

	@Autowired
	AccountService service;

	@GetMapping
	public List<Account> getAllAccounts()
	{
		List<Account> accounts = service.getAllAccounts();
		return accounts;
	}
	
	
	public void updateBalance(Transaction account)
	{
		Optional<Account> optional = service.getAccount(account.getAccountNumber());
		Account accountUpdate = optional.get();
		accountUpdate.setCurrentBalance(account.getCurrentBalance());
		System.out.println(accountUpdate.toString());
		service.update(accountUpdate);
 	}

	@GetMapping("/{accountNumber}")
	public Optional<Account> getAccountById(@PathVariable int accountNumber)
	{
		Optional<Account> account = service.getAccount(accountNumber);
		return account;
	}
	
	@GetMapping("/{accountNumber}/balance")
	public double getCurrentBalance(@PathVariable int accountNumber)
	{
		Optional<Account> account = service.getAccount(accountNumber);
		double currentBalance = account.get().getCurrentBalance();
		return currentBalance;
	}
	
	@DeleteMapping("/{accountNumber}")
	public void deleteAccount(@PathVariable int accountNumber)
	{
		service.deleteAccount(accountNumber);
	}
	
	  @PutMapping("/{accountNumber}") 
	  public void updateBalance(@RequestParam("currentBalance") double currentBalance,@PathVariable int accountNumber) 
	  { 
		  Optional<Account> optional= service.getAccount(accountNumber);
		  Account account = optional.get();
		  account.setCurrentBalance(currentBalance); 
		  service.update(account); 
	  }
	 
}
