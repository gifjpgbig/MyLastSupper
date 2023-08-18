package com.projectdemo.order.bean;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.menu.bean.DishBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_detail")
//@NamedEntityGraph(name = "OrderDetailBean.dish", attributeNodes = @NamedAttributeNode("dish"))
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class OrderDetailBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;


	@ManyToOne
	@JoinColumn(name = "fk_dish_id", columnDefinition = "int", referencedColumnName = "id")
	private DishBean dish;

	@ManyToOne
	@JoinColumn(name = "fk_order_list_id", columnDefinition = "int", referencedColumnName = "id")
	private OrderListBean orderList;

	@Column(name = "customization", columnDefinition = "nvarchar(MAX)")
	private String customization;

	@Column(name = "total_price", columnDefinition = "int")
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
