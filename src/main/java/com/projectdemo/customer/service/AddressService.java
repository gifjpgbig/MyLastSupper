package com.projectdemo.customer.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.projectdemo.customer.bean.AddressBean;
import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.repository.AddressRepository;
import com.projectdemo.customer.repository.CustomerRepository;

@Configuration
public class AddressService {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	public AddressBean createAddress(AddressBean address,Integer id) {
		Optional<CustomerBean> customerOptional = customerRepository.findById(id);
		if(customerOptional.isPresent()) {
			CustomerBean customer = customerOptional.get();
			address.setCustomer(customer);
			return addressRepository.save(address);
		} else {
			return null;
		}
		
	}
	
	public AddressBean AddressRead(Integer id) {
		return addressRepository.findById(id).get();
	}
	
	public AddressBean addressFindByID(Integer id) {
		return addressRepository.findAddressByID(id);
	}
}
