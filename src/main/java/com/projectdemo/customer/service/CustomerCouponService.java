package com.projectdemo.customer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.customer.bean.CouponBean;
import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.customer.bean.CustomerCouponBean;
import com.projectdemo.customer.repository.CouponRepository;
import com.projectdemo.customer.repository.CustomerCouponRepository;
import com.projectdemo.customer.repository.CustomerRepository;

@Service
public class CustomerCouponService {
	
	@Autowired
	private CustomerCouponRepository customerCouponRepository;
	@Autowired
	private CustomerRepository customerRepository;
	@Autowired
	private CouponRepository couponRepository;
	
	public void createCustomerCoupon(Integer customerID,Integer couponID) {
		CustomerBean customer = customerRepository.findById(customerID).get();
		CouponBean coupon = couponRepository.findById(couponID).get();
		CustomerCouponBean customerCoupon = new CustomerCouponBean();
		customerCoupon.setCustomer(customer);
		customerCoupon.setCoupon(coupon);
		customerCoupon.setDescription(coupon.getDescription());
		customerCoupon.setUserUsageLimit(5);
		customerCouponRepository.save(customerCoupon);
	}
}
