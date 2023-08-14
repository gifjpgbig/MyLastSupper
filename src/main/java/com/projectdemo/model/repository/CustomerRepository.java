package com.projectdemo.model.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.customer.CustomerBean;

public interface CustomerRepository extends JpaRepository<CustomerBean, Integer> {
	
}
