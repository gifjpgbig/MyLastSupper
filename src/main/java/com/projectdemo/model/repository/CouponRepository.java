package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.customer.CouponBean;

public interface CouponRepository extends JpaRepository<CouponBean, Integer> {

}
