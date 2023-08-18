package com.projectdemo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.CustomerRepository;

@Service
public class CustomerService {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerBean createCustomer(CustomerBean customer) {
		return customerRepository.save(customer);
	}
	
	public CustomerBean findCustomerByName(String name) {
		return customerRepository.findCustomerByName(name);
	}
	
	public CustomerBean findCustomerById(Integer id) {
		return customerRepository.findById(id).get();
	}
}
