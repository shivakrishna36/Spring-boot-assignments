package com.moneymoney.web.controller;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import com.moneymoney.web.entity.Transaction;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@Controller
public class BankAppController {
	
	@Autowired
	private RestTemplate restTemplate;
	
	@RequestMapping("/")
	public String home() {
		return "index";
	}
	
	@RequestMapping("/depositForm")
	public String depositForm() {
		return "DepositForm";
	}
	
	@HystrixCommand(defaultFallback = "defaultStatements")
	@RequestMapping("/deposit")
	public String deposit(@ModelAttribute Transaction transaction,
			Model model) {
		restTemplate.postForEntity("http://transaction/transactions", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "DepositForm";
	}
	
	@RequestMapping("/withdrawForm")
	public String withdrawForm() {
		return "withdrawForm";
	}
	
	@HystrixCommand(defaultFallback = "defaultStatements")
	@RequestMapping("/withdraw")
	public String withdraw(@ModelAttribute Transaction transaction,
			Model model) {
		restTemplate.postForEntity("http://transaction/transactions/withdraw", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "withdrawForm";
	}
	
	@RequestMapping("/transferForm")
	public String transfer() {
		return "transferForm";
	}
	
	@HystrixCommand(defaultFallback = "defaultStatements")
	@RequestMapping("/transferMoney")
	public String fundTransfer(@RequestParam("senderNumber") int senderAccount,
			@RequestParam("receiverNumber") int receiverAccount,
			@ModelAttribute Transaction transaction,
			Model model) {
		transaction.setAccountNumber(senderAccount);
		restTemplate.postForEntity("http://transaction/transactions/withdraw", 
				transaction, null);
		transaction.setAccountNumber(receiverAccount);
		restTemplate.postForEntity("http://transaction/transactions", 
				transaction, null);
		model.addAttribute("message","Success!");
		return "transferForm";
	}
	
	
	@RequestMapping("/getStatements")
	public ModelAndView getStatement(@RequestParam("offset") int offset,@RequestParam("size") int size)
	{
		CurrentDataSet dataset = restTemplate.getForObject("http://transaction/transactions/getstatements",CurrentDataSet.class);
		int currentSize = size ==0?5:size;
		int currentOffset = offset ==0?1:offset;
		Link next = linkTo(methodOn(BankAppController.class).getStatement(size+offset, currentSize)).withRel("next");
		Link previous = linkTo(methodOn(BankAppController.class).getStatement(offset-size, currentSize)).withRel("previous");
		List<Transaction> currentDataSet = new ArrayList<Transaction>();
		List<Transaction> transactions = dataset.getTransactions();
		for (int i = currentOffset - 1; i < currentSize + currentOffset - 1; i++) 
		{
			if(currentOffset<1)
				break;
			Transaction transaction = transactions.get(i);
			currentDataSet.add(transaction);
		}
		CurrentDataSet datasetList =  new CurrentDataSet(currentDataSet,next,previous);
		return new ModelAndView("statements","currentDataSet",datasetList);
	}
	
	public String defaultStatements( )
	{
		return "error";
	}
	
	/*
	 * public String fundError(@RequestParam("senderNumber") int senderAccount,
	 * 
	 * @RequestParam("receiverNumber") int receiverAccount,
	 * 
	 * @ModelAttribute Transaction transaction, Model model) { return "error"; }
	 */
	
}
