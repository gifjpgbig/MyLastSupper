package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.ShopBean;

public interface CannedMessageRepository extends JpaRepository<CannedMessageBean, Integer> {
	List<CannedMessageBean> findAllByShop(ShopBean shop);
	
	@Query(value = "select * from canned_message where fk_shop_id=:id ORDER BY CAST(message_cdate as datetime) + CAST(message_send_time as datetime)", nativeQuery = true)
	List<CannedMessageBean> findAllByShopOrdered(Integer id);
}
