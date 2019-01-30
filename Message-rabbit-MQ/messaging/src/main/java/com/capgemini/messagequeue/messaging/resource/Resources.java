package com.capgemini.messagequeue.messaging.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.capgemini.messagequeue.messaging.service.ServiceImpl;

@Controller
public class Resources {

	@Autowired
	ServiceImpl service;
	
	@RequestMapping("/")
	public void sender()
	{
		String message = "Hello";
		service.send(message);
	}
	
}
