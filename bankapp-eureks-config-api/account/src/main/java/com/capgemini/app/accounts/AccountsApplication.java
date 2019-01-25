package com.capgemini.app.accounts;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

import com.capgemini.app.accounts.bankaccounts.CurrentAccount;
import com.capgemini.app.accounts.bankaccounts.SavingsAccount;
import com.capgemini.app.accounts.repository.AccountRepository;

@EnableDiscoveryClient
@SpringBootApplication
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

	
	@Bean
	public CommandLineRunner createAccount(AccountRepository repo)
	{
		return (arg) ->{
		repo.save(new SavingsAccount(101, "Shiva", 15000.0, true));
		repo.save(new SavingsAccount(102, "Krishna", 15000.0, false));
		repo.save(new SavingsAccount(103, "Neha", 0.0, true));
		repo.save(new CurrentAccount(104, "Jaffer", 15000.0, 100.0));
		repo.save(new CurrentAccount(105, "krish", 15000.0, 100.0));
		};
	
	}
}

