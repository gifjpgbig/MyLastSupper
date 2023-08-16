package com.projectdemo.order.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.shop.bean.ShopBean;

public interface OrderListRepository extends JpaRepository<OrderListBean, Integer> {

	// 用客戶ID找
	@Query(value = "select * from order_list where fk_customer_id =:n", nativeQuery = true)
	Page<OrderListBean> findByCustomerId(@Param("n") Integer customerId, Pageable page);

	// 用店家ID找
	@Query(value = "select * from order_list where fk_shop_id =:n", nativeQuery = true)
	Page<OrderListBean> findByShopId(@Param("n") Integer shopId, Pageable page);

//	@Query(value = "select * from order_list", nativeQuery = true)
//	List<OrderListBean> testAll();
//	@Query(value = "select * from OrderListBean", nativeQuery = true)
//	List<OrderListBean> testAll();

	// 客戶歷史訂單
	List<OrderListBean> findByCustomer(CustomerBean customer);

	// 店家歷史訂單
	List<OrderListBean> findByShop(ShopBean shop);
	// 改變訂單狀態

	// 改變客戶針對訂單的評論、店家的評價、餐點的評論 done
	// 改變店家回覆客戶的評論 done
	
	// 讓外送員查看現在可以接的訂單 done
		// 現在可以接的訂單邏輯是: 客戶、餐廳訂單狀態不屬於已棄單、外送訂單狀態不屬於已接單
		// 還沒被其他外送員接走的訂單
		// 被其他外送員放棄的訂單
	@Query(value = "select * from order_list where shop_status not like '%棄%' and cus_status not like '%棄%' and deliver_status not like '已接%'", nativeQuery = true)
	List<OrderListBean> findIsOrderTakable();
	
	
	@Query(value = "select * from order_list where shop_status not like '%棄%' and cus_status not like '%棄%'and not deliver_status = '已完成'", nativeQuery = true)
	Page<OrderListBean> findAllInProgress(Pageable page);
	
	
	

}
