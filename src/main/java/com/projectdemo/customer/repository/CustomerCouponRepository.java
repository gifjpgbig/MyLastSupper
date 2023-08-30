package com.projectdemo.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.CustomerCouponBean;

public interface CustomerCouponRepository extends JpaRepository<CustomerCouponBean, Integer>{
	
	@Query(value = "SELECT * FROM customer_coupon WHERE fk_coupon_id = :coupon AND fk_customer_id = :cid" ,nativeQuery = true)
	CustomerCouponBean checkIfExists(@Param("cid")Integer cid,@Param("coupon") Integer coupon);
	
	@Query(value = "SELECT * FROM customer_coupon WHERE fk_customer_id = :cid" ,nativeQuery = true)
	List<CustomerCouponBean> findCustomerCouponByCusID(@Param("cid")Integer cid);
}
