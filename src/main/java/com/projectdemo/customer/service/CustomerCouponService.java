package com.projectdemo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.repository.CustomerRepository;

@Service
public class CustomerCouponService {
	
	@Autowired
	private CustomerRepository customerRepository;
}
