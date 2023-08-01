package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.ShopBean;

public interface CannedMessageRepository extends JpaRepository<CannedMessageBean, Integer> {
	List<CannedMessageBean> findAllByShop(ShopBean shop);
}
