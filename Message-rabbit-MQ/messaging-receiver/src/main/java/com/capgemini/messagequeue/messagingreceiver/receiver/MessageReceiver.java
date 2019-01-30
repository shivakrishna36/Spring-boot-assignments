package com.capgemini.messagequeue.messagingreceiver.receiver;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class MessageReceiver {

	@RabbitListener(queues = "Customer")
	public void processMessage(String message)
	{
		System.out.println(message);
	}
	
	@Bean
	public Queue queue()
	{
		return new Queue("Customer",false);
	}
	
}
