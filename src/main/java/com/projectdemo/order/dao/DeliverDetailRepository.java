package com.projectdemo.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.order.bean.DeliverDetailBean;

public interface DeliverDetailRepository extends JpaRepository<DeliverDetailBean, Integer>{

	@Query(value = "select * from deliver_detail where fk_order_list_id = :n", nativeQuery = true)
	List<DeliverDetailBean> findDDByOrderId(@Param("n") Integer id);
	
	
	//判斷該筆訂單能不能被接單，檢查訂單狀態是否有進行中的外送，在deliver_detail中檢查fk_order_list_id=?，is_cancel=0 and is_complete=0
	@Query(value = "select * from deliver_detail where fk_order_list_id = :n and is_complete = 0 and is_cancel = 0", nativeQuery = true)
	DeliverDetailBean checkTakable(@Param("n") Integer id);
		
	
	@Query(value = "  select  dd.address as cus_address, dd.deliver_time, ol.delivery_fee, ol.order_time, s.address as shop_address, s.name as shop_name\r\n"
			+ "  from deliver_detail as dd, order_list as ol, shop as s where dd.fk_order_list_id = ol.id and ol.fk_shop_id = s.id  and is_cancel = 0 and is_complete = 0 and fk_deliverer_id = 1", nativeQuery = true)
	List<DeliverDetailBean> findInProgressByDeliver();
	
	
	
	
}
