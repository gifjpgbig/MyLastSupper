package com.projectdemo.manage.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.manage.bean.CustomerServiceBean;

public interface CustomerServiceRepository extends JpaRepository<CustomerServiceBean, Integer>{

}
