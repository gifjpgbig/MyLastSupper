package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;

public interface PrepTimeRepository extends JpaRepository<PrepTimeBean, Integer> {
	List<PrepTimeBean> findAllByShop(ShopBean shop);
	
	//  select * from prep_time where fk_shop_id=1 order by day, start_time
	@Query(value = "select * from prep_time where fk_shop_id=:id ORDER BY day, start_time", nativeQuery = true)
	List<PrepTimeBean> findAllByShopOrdered(Integer id);
}
