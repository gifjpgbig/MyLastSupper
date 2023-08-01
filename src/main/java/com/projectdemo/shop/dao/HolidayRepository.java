package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.ShopBean;

public interface HolidayRepository extends JpaRepository<HolidayBean, Integer> {
	List<HolidayBean> findAllByShop(ShopBean shop);
}
