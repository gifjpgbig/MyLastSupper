package com.projectdemo.manage.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import com.projectdemo.manage.bean.CustomerServiceBean;

public interface CustomerServiceRepository extends JpaRepository<CustomerServiceBean, Integer>{

	@Query(value = "select * from customer_service where account = :n", nativeQuery = true)
	CustomerServiceBean findCustomerServiceByAccount(@Param("n") String account);

}
