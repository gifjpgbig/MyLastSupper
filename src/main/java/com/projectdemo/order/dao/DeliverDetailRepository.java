package com.projectdemo.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.order.bean.DeliverDetailBean;

public interface DeliverDetailRepository extends JpaRepository<DeliverDetailBean, Integer>{

	@Query(value = "select * from deliver_detail where fk_order_list_id = :n", nativeQuery = true)
	List<DeliverDetailBean> findDDByOrderId(@Param("n") Integer id);
	
	

	
}
