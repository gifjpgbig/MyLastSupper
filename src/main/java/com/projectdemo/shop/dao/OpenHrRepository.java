package com.projectdemo.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.OpenHrBean;
import com.projectdemo.shop.bean.ShopBean;

public interface OpenHrRepository extends JpaRepository<OpenHrBean, Integer> {
	OpenHrBean findByShop(ShopBean shopBean);
}
