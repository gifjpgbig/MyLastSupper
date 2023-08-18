package com.projectdemo.model.bean.order;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.model.bean.menu.DishBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "order_detail")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class OrderDetailBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_dish_id", nullable = false, columnDefinition = "int")
	private DishBean dish;
	
	@ManyToOne
	@JoinColumn(name = "fk_order_list_id", columnDefinition = "int")
	private OrderListBean orderList;
	
	@Column(name = "customization" , columnDefinition = "nvarchar(MAX)")
	private String customization;

	@Column(name = "price", columnDefinition = "int")
	private Integer totalPrice;

	@Column(name = "amount", columnDefinition = "int")
	private Integer amount;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DishBean getDish() {
		return dish;
	}

	public void setDish(DishBean dish) {
		this.dish = dish;
	}

	public OrderListBean getOrderList() {
		return orderList;
	}

	public void setOrderList(OrderListBean orderList) {
		this.orderList = orderList;
	}

	public String getCustomization() {
		return customization;
	}

	public void setCustomization(String customization) {
		this.customization = customization;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
	
	
	
}
