package com.projectdemo.shop.dao;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.shop.bean.ShopBean;

public interface ShopRepository extends JpaRepository<ShopBean, Integer>{
	
	// Find using name
	@Query("from ShopBean where name=:name")
	ShopBean findByName(@Param("name") String name);
	
	// Fuzzy Search
	@Query("from ShopBean where name like %:name%")
	List<ShopBean> findFuzzy(@Param("name") String name);
	
	// Find by account name
	@Query("from ShopBean where account=:account")
	ShopBean findByAccount(@Param("account") String account);
	
	// Pagination
	@Query(value = "select * from shop", nativeQuery = true)
	Page<ShopBean> findAllPage(Pageable page);
}
