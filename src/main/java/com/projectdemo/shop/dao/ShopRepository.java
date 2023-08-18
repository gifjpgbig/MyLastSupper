package com.projectdemo.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.ShopBean;

public interface ShopRepository extends JpaRepository<ShopBean, Integer>{

}
