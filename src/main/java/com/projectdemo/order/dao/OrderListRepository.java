package com.projectdemo.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.shop.bean.ShopBean;

public interface OrderListRepository extends JpaRepository<OrderListBean, Integer>{

//	@Query(" * from order_list where fk_customer_id =:n")
//	
//	OLBList<OrderListBean> findByCustomerId(Integer customerId);
//	
	//客戶歷史訂單
	List<OrderListBean> findByCustomer(CustomerBean customer);
	//店家歷史訂單
	List<OrderListBean> findByShop(ShopBean shop);
	//改變訂單狀態
	
	//改變客戶針對訂單的評論、店家的評價、餐點的評論
	//改變店家回覆客戶的評論
	
	
	
	
}
