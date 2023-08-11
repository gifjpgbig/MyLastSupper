package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;

public interface ShopCategoryRepository extends JpaRepository<ShopCategoryBean, Integer> {
	List<ShopCategoryBean> findAllByShop(ShopBean shop);
}
