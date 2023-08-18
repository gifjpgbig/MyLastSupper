package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.service.CustomerCouponService;

@RestController
@CrossOrigin()
public class CustomerCouponController {
	
	@Autowired
	CustomerCouponService customerCouponService;
	
	
	@PostMapping("/customerCoupon/create/{customerID}/{couponID}")
	public void createCustomerCoupon(@PathVariable Integer customerID,@PathVariable Integer couponID){
		customerCouponService.createCustomerCoupon(customerID, couponID);
	}
}
