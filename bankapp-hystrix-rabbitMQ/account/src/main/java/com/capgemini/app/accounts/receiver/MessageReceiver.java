package com.capgemini.app.accounts.receiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.capgemini.app.accounts.bankaccounts.Account;
import com.capgemini.app.accounts.resource.AccountResource;
import com.capgemini.app.transactions.entity.Transaction;

@Component
public class MessageReceiver {

	@Autowired
	AccountResource resource;
	
	@Bean
	public Queue queue()
	{
		return new Queue("transaction",false);
	}
	
	@RabbitListener(queues = "transaction")
	public void update(Transaction account)
	{
		resource.updateBalance(account);
	}
}
