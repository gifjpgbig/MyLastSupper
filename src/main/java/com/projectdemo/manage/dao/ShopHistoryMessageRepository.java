package com.projectdemo.manage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.manage.bean.ShopHistoryMessageBean;

public interface ShopHistoryMessageRepository extends JpaRepository<ShopHistoryMessageBean, Integer>{

}
