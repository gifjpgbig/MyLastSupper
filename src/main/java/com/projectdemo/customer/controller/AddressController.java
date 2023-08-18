package com.projectdemo.customer.controller;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.AddressBean;
import com.projectdemo.customer.service.AddressService;

@RestController
@CrossOrigin()
public class AddressController {
	
	@Autowired
	private AddressService addressService;
		
	@PostMapping("/address/create/{id}")
	public String addressCreate(@RequestBody AddressBean address, @PathVariable Integer id) {
		addressService.createAddress(address, id);
		JSONObject responseJson = new JSONObject();
		responseJson.put("message", "新增成功");
		responseJson.put("success", true);
		return responseJson.toString();
		
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
