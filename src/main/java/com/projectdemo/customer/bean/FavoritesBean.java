package com.projectdemo.customer.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.shop.bean.ShopBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "favorites")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class FavoritesBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fk_shop_id", nullable = false)
	private ShopBean shop;

	@ManyToOne
	@JoinColumn(name = "customerid", columnDefinition = "int")
	private CustomerBean customer;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public ShopBean getShop() {
		return shop;
	}
	public void setShop(ShopBean shop) {
		this.shop = shop;
	}
	public CustomerBean getCustomer() {
		return customer;
	}
	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	
	
	
}
