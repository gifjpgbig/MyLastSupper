package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;

public interface PrepTimeRepository extends JpaRepository<PrepTimeBean, Integer> {
	List<PrepTimeBean> findAllByShop(ShopBean shop);
}
