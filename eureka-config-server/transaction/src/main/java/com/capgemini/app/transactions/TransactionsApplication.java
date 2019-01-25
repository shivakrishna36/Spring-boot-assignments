package com.capgemini.app.transactions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.capgemini.app.transactions.entity.Transaction;
import com.capgemini.app.transactions.entity.TransactionType;
import com.capgemini.app.transactions.repository.TransactionRepository;
import com.capgemini.app.transactions.service.TransactionService;

@EnableDiscoveryClient
@SpringBootApplication
public class TransactionsApplication {

	@Autowired
	TransactionService service;
	
	public static void main(String[] args) {
		SpringApplication.run(TransactionsApplication.class, args);
	}

	
	@Bean
	@LoadBalanced
	public RestTemplate getTemplate()
	{
		return new RestTemplate();
	}
	
	@Bean
	public CommandLineRunner getCommand(TransactionRepository repository)
	{
		return (evt) ->{
		repository.save(new Transaction(101, 5000.0, TransactionType.DEPOSIT, "ATM"));
		service.deposit(101, 1000.0, 58, "ATM");
		service.deposit(102, 1500.0, 53, "Website");
		service.deposit(103, 1800.0, 54, "Other Account");
		service.deposit(104, 2000.0, 50, "Bank");
		};
	}
}

