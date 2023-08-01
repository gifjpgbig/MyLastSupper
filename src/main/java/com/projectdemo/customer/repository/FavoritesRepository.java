package com.projectdemo.customer.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.customer.bean.FavoritesBean;

public interface FavoritesRepository extends JpaRepository<FavoritesBean, Integer> {

}
