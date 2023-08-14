package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.customer.AddressBean;

public interface AddressRepository extends JpaRepository<AddressBean, Integer> {

}
