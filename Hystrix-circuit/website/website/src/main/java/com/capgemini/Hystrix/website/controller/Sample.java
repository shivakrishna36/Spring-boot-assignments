package com.capgemini.Hystrix.website.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
public class Sample {

	@Autowired
	RestTemplate template;
	
	@RequestMapping("/")
	@HystrixCommand(fallbackMethod = "goBack")
	public String hello()
	{
		return template.getForEntity("http://localhost:8111/",String.class).getBody();
	}
	
	public String goBack()
	{
		return "GO BACK TO HOME";
	}

}
