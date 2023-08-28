package com.projectdemo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.customer.bean.CustomerCouponBean;

public interface CustomercouponRepository extends JpaRepository<CustomerCouponBean, Integer>{

}
