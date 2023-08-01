package com.projectdemo.order.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.order.bean.DeliverDetailBean;

public interface DeliverDetailRepository extends JpaRepository<DeliverDetailBean, Integer>{

}
