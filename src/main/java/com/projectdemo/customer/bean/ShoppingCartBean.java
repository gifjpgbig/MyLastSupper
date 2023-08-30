package com.projectdemo.customer.bean;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.shop.bean.ShopBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "shopping_cart")
//@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ShoppingCartBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "fk_customer_id",referencedColumnName = "id", columnDefinition = "int")
	private CustomerBean customer;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "fk_dish_id",referencedColumnName = "id",columnDefinition = "int")
	private DishBean dish;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "fk_shop_id",referencedColumnName = "id",columnDefinition = "int")
	private ShopBean shop;

	@Column(name = "customization", columnDefinition = "nvarchar(MAX)")
	private String customization;

	@Column(name = "dish_price", columnDefinition = "int")
	private Integer dishPrice;

	@Column(name = "amount", columnDefinition = "int")
	private Integer amount;

	// 創建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	// 最新更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", columnDefinition = "datetime")
	private LocalDateTime updateDate;

	@Column(name = "is_purchased", columnDefinition = "bit")
	private boolean isPurchased;

	@Column(name = "devliery_fee", columnDefinition = "int")
	private Integer devlieryFee;

	@Column(name = "discount", columnDefinition = "int")
	private Integer discount;

	@Column(name = "totalPrice", columnDefinition = "int")
	private Integer totalPrice;

	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		}
	}
	
	@PreUpdate
	public void onUpdate() {
		updateDate = LocalDateTime.now();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public DishBean getDish() {
		return dish;
	}

	public void setDish(DishBean dish) {
		this.dish = dish;
	}

	public ShopBean getShop() {
		return shop;
	}

	public void setShop(ShopBean shop) {
		this.shop = shop;
	}

	public String getCustomization() {
		return customization;
	}

	public void setCustomization(String customization) {
		this.customization = customization;
	}

	public Integer getDishPrice() {
		return dishPrice;
	}

	public void setDishPrice(Integer dishPrice) {
		this.dishPrice = dishPrice;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isPurchased() {
		return isPurchased;
	}

	public void setPurchased(boolean isPurchased) {
		this.isPurchased = isPurchased;
	}

	public Integer getDevlieryFee() {
		return devlieryFee;
	}

	public void setDevlieryFee(Integer devlieryFee) {
		this.devlieryFee = devlieryFee;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

}
