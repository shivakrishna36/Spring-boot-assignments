package com.capgemini.Zuul.discoveryweb;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@SpringBootApplication
public class DiscoveryWebApplication {

	@Autowired
	RestTemplate template;
	
	public static void main(String[] args) {
		SpringApplication.run(DiscoveryWebApplication.class, args);
	}

	@Bean
	@LoadBalanced
	public RestTemplate temp()
	{
		return new RestTemplate();
	}
	
	@RequestMapping("/")
	public ResponseEntity<String> hi()
	{
		return template.getForEntity("http://CLIENT/hello",String.class);
	}
	
}

