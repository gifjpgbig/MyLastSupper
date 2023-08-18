package com.projectdemo.order.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.order.bean.DeliverDetailBean;
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
	
	
	
	

	// 讓外送員查看現在可以接的訂單 done
		// 現在可以接的訂單邏輯是: 客戶、餐廳訂單狀態不屬於已棄單、外送訂單狀態不屬於已接單
		// 還沒被其他外送員接走的訂單
		// 被其他外送員放棄的訂單
	@Query(value = "select * from order_list where shop_status not like '%棄%' and cus_status not like '%棄%' and deliver_status not like '已接%'", nativeQuery = true)
	List<OrderListBean> findIsOrderTakable();
	
	
	@Query(value = "select * from order_list where shop_status not like '%棄%' and cus_status not like '%棄%'and not deliver_status = '已完成'", nativeQuery = true)
	Page<OrderListBean> findAllInProgress(Pageable page);
	
	
//	@Query(value = "select dd.address as cus_address, dd.deliver_time, ol.delivery_fee, ol.order_time, s.address as shop_address, s.name as shop_name from deliver_detail as dd, order_list as ol, shop as s where dd.fk_order_list_id = ol.id and ol.fk_shop_id = s.id  and is_cancel = 0 and is_complete = 0 and fk_deliverer_id = 1", nativeQuery = true)
//	@Query(value = "select * from deliver_detail as dd, order_list as ol where dd.fk_order_list_id = ol.id and is_cancel = 0 and is_complete = 0 and fk_deliverer_id = 1", nativeQuery = true)
//	@Query(value = "select * from deliver_detail as dd, order_list as ol where dd.fk_order_list_id = ol.id", nativeQuery = true)
	@Query(value = "select * from order_list", nativeQuery = true)
	List<OrderListBean> findInProgressByDeliver();
	
	

}
