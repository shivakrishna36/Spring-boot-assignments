package com.capgemini.app.transactions.sender;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import com.capgemini.app.transactions.entity.Transaction;

@Component
public class Sender 
{

	@Autowired
	RabbitMessagingTemplate template;
	
	@Bean
	public Queue queue()
	{
		return new Queue("transaction",false);
	}
	
	public void send(Transaction account)
	{
		template.convertAndSend("transaction",account);
	}
	
}
