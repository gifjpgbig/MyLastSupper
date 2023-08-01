package com.projectdemo.customer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.customer.bean.CouponBean;
import com.projectdemo.customer.service.CouponService;

@RestController
public class CouponController {
	
	@Autowired
	private CouponService couponService;
	
	@PostMapping("/coupon/create")
	public CouponBean createCoupon(@RequestBody CouponBean coupon) {
		return couponService.createCoupon(coupon);
	}
	
	@GetMapping("/coupon/findByID/{id}")
	public CouponBean findCouponByID(@PathVariable Integer id) {
		return couponService.findCouponByID(id);
	}
}
