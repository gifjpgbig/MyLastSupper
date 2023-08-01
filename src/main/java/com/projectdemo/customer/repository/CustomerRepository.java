package com.projectdemo.customer.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	
	@Query(value = "select * from customer where name = :name" , nativeQuery = true)
	CustomerBean findCustomerByName(@Param("name") String name);
}
