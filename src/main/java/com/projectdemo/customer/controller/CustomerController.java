package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.service.CustomerService;

@RestController
public class CustomerController {
	
	@Autowired
	private CustomerService customerservice;
	
	@PostMapping("/customer/create")
	public CustomerBean createCustomer(@RequestBody CustomerBean customer) {
		return customerservice.createCustomer(customer);
	}
	
	@GetMapping("/customer/findByName")
	public CustomerBean findCustomerByName(@RequestParam("name") String name) {
		return customerservice.findCustomerByName(name);
	}
	
	@GetMapping("/customer/find{id}")
	public CustomerBean findCustomerById(@PathVariable Integer id) {
		return customerservice.findCustomerById(id);
	}
}
