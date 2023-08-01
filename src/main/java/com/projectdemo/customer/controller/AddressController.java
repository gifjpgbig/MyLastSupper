package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.AddressBean;
import com.projectdemo.customer.service.AddressService;

@RestController
public class AddressController {
	
	@Autowired
	private AddressService addressService;
		
	@PostMapping("/address/create/{id}")
	public AddressBean addressCreate(@RequestBody AddressBean address, @PathVariable Integer id) {
		return addressService.createAddress(address, id);
	}
	
	@GetMapping("/address/read/{id}")
	public AddressBean addressRead(@PathVariable Integer id) {
		return addressService.AddressRead(id);
	}
	
	@GetMapping("/address/findByID/{id}")
	public AddressBean addressFindByID(@PathVariable Integer id) {
		return addressService.addressFindByID(id);
	}
}
