package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.ShopBean;

public interface HolidayRepository extends JpaRepository<HolidayBean, Integer> {
	List<HolidayBean> findAllByShop(ShopBean shop);
	
	@Query(value= "select * from holiday where fk_shop_id=:id AND rest_date > GETDATE() ORDER BY rest_date",nativeQuery = true)
	List<HolidayBean> findActiveHoliday(@Param("id") Integer id);
}
