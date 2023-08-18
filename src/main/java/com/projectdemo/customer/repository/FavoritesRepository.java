package com.projectdemo.customer.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.projectdemo.customer.bean.FavoritesBean;

public interface FavoritesRepository extends JpaRepository<FavoritesBean, Integer> {
	
	
	@Query(value = "SELECT * FROM favorites WHERE customerid = :cid",nativeQuery = true)
	List<FavoritesBean> findFavoriteShopByCusID(@Param("cid")Integer cid);
}
