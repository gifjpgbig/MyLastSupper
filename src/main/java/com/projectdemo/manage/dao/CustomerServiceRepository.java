package com.projectdemo.manage.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.manage.bean.CustomerServiceBean;

public interface CustomerServiceRepository extends JpaRepository<CustomerServiceBean, Integer>{

	@Query(value = "select * from customer_service where account = :n", nativeQuery = true)
	CustomerServiceBean findCustomerServiceByAccount(@Param("n") String account);

	
	@Query(value = "select * from customer_service where uid = :n", nativeQuery = true)
	CustomerServiceBean findCSBByUID(@Param("n") String uid);
	
	
	@Query(value = "select * from customer_service where authorizations = :n", nativeQuery = true)
	List<CustomerServiceBean> findByAuth(@Param("n") String auth);
	
}
