package com.projectdemo.shop.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.shop.bean.OpenHrBean;

public interface OpenHrRepository extends JpaRepository<OpenHrBean, Integer> {
	
}
