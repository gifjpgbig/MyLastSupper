package com.projectdemo.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.model.bean.customer.AddressBean;
import com.projectdemo.model.repository.AddressRepository;

@Configuration
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	public AddressBean addAddress(AddressBean Address) {
		return addressRepository.save(Address);
	}
}
