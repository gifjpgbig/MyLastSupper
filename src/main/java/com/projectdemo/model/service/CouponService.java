package com.projectdemo.model.service;

import org.springframework.context.annotation.Configuration;

import com.projectdemo.model.bean.customer.CouponBean;
import com.projectdemo.model.repository.CouponRepository;

@Configuration
public class CouponService {
	
	private CouponRepository CouponRepository;
	
	public CouponBean findAddress(CouponBean Coupon) {
		return CouponRepository.save(Coupon);
	}
}