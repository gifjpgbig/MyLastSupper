package com.projectdemo.deliver.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.deliver.bean.TransportationBean;

public interface TransportationRepository extends JpaRepository<TransportationBean, Integer> {

	@Transactional
	@Modifying
	@Query("update TransportationBean set type=:type, brand=:brand, license=:license where id=:id")
	void updateById(@Param("id")Integer id,@Param("type")String type,@Param("brand")String brand,@Param("license")String license);
}