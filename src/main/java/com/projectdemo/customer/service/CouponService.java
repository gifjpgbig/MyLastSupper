package com.projectdemo.customer.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CouponBean;
import com.projectdemo.customer.repository.CouponRepository;

@Service
public class CouponService {
	
	@Autowired
	private CouponRepository CouponRepository;
	
	public CouponBean createCoupon(CouponBean Coupon) {
		return CouponRepository.save(Coupon);
	}
	
	public CouponBean findCouponByID(Integer id) {
		return CouponRepository.findCouponByID(id);
	}
	
	public List<CouponBean> findAllCoupon(){
		return CouponRepository.findAll();
	}
}