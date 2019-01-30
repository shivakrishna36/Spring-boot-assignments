package com.capgemini.messagequeue.messaging.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.messagequeue.messaging.sender.Sender;

@Service
public class ServiceImpl {

	@Autowired
	Sender sender;
	
	public void send(String message)
	{
		sender.send(message);
	}
	
}
