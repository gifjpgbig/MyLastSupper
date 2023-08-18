package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.projectdemo.model.bean.shop.ShopBean;

public interface ShopRepository extends JpaRepository<ShopBean, Integer> {
	
	@Modifying
    @Transactional
    @Query(value = "UPDATE shop SET photo = :photo   WHERE id = :id", nativeQuery = true)
    void updatePhotoByID(@Param("id") Integer id , @Param("photo") byte[] photoBytes);
	

}

