package com.projectdemo.order.dao;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.order.bean.OrderDetailBean;

public interface OrderDetailRepository extends JpaRepository<OrderDetailBean, Integer>{

//    @EntityGraph(attributePaths = {"dish"})
//    Optional<OrderDetailBean> findById(Integer id);

//    @EntityGraph(attributePaths = {"orderList","dish","dish.menu","dish.menu.shop","orderList.customer","orderList.shop"})
//    OrderDetailBean findOrderDetailById(Integer id);
	
	@Query(value = "select * from order_detail where fk_dish_id = :n", nativeQuery = true)
	List<OrderDetailBean> findOrderDetailById(@Param("n") Integer id);
	
}
