package com.projectdemo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.customer.bean.CustomerCouponBean;

public interface CustomerCouponRepository extends JpaRepository<CustomerCouponBean, Integer>{
	
}
