package com.projectdemo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.CouponBean;

public interface CouponRepository extends JpaRepository<CouponBean, Integer> {
	
	@Query(value = "Select * From coupon where id = :id" ,nativeQuery = true)
	CouponBean findCouponByID(@Param("id") Integer id);
}
