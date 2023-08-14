package com.projectdemo.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projectdemo.model.bean.customer.FavoritesBean;

public interface FavoritesRepository extends JpaRepository<FavoritesBean, Integer> {

}
