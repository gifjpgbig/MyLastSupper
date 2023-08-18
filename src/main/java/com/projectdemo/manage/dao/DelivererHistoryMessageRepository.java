package com.projectdemo.manage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.manage.bean.DelivererHistoryMessageBean;

public interface DelivererHistoryMessageRepository extends JpaRepository<DelivererHistoryMessageBean, Integer>{

}
