package com.projectdemo.menu.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.order.bean.OrderDetailBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "dish")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DishBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dish", fetch= FetchType.EAGER)
	@JsonIgnore
	private List<OrderDetailBean> orderDetail;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
	@JsonIgnore
	private List<ShoppingCartBean> shoppingCart;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "dish")
	@JsonIgnore
	private List<MenuCustomizationBean> menuCustomization;

//	@JsonIgnoreProperties({"menu"})
	@ManyToOne
	@JoinColumn(name = "fk_menu_id", columnDefinition = "int")
	private MenuBean menu;

	@Column(name = "name", columnDefinition = "nvarchar(MAX)")
	private String name;

	@Column(name = "description", columnDefinition = "nvarchar(MAX)")
	private String Description;

	@Column(name = "price", columnDefinition = "int")
	private Integer Price;

	// 客戶大頭貼
	@Column(name = "picture", columnDefinition = "varbinary(max)")
	private byte[] picture;

	@Column(name = "extra_info", columnDefinition = "nvarchar(MAX)")
	private String ExtraInfo;

	@Column(name = "review", columnDefinition = "int")
	private Integer Review;

	// 創建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	// 最新更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	//飲食限制
	@Column(name = "dietary_restrictions" , columnDefinition = "nvarchar(MAX)")
	private String dietaryRestrictions;
	
	@Column(name = "likes" , columnDefinition = "int")
	private Integer likes;
	
	@Column(name = "dislikes" , columnDefinition = "int")
	private Integer dislikes;
	
	@Column(name = "likerate" , columnDefinition = "float")
	private float likerate;
	
	@Column(name = "sold_out" , columnDefinition = "bit")
	private boolean soldOut;
	
	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		}
	}
	

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MenuBean getMenu() {
		return menu;
	}

	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return Description;
	}

	public void setDescription(String description) {
		Description = description;
	}

	public Integer getPrice() {
		return Price;
	}

	public void setPrice(Integer price) {
		Price = price;
	}

	public byte[] getPicture() {
		return picture;
	}

	public void setPicture(byte[] picture) {
		this.picture = picture;
	}

	public String getExtraInfo() {
		return ExtraInfo;
	}

	public void setExtraInfo(String extraInfo) {
		ExtraInfo = extraInfo;
	}

	public Integer getReview() {
		return Review;
	}

	public void setReview(Integer review) {
		Review = review;
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

	public String getDietaryRestrictions() {
		return dietaryRestrictions;
	}

	public void setDietaryRestrictions(String dietaryRestrictions) {
		this.dietaryRestrictions = dietaryRestrictions;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getDislikes() {
		return dislikes;
	}

	public void setDislikes(Integer dislikes) {
		this.dislikes = dislikes;
	}

	public float getLikerate() {
		return likerate;
	}

	public void setLikerate(float likerate) {
		this.likerate = likerate;
	}

	public boolean isSoldOut() {
		return soldOut;
	}

	public void setSoldOut(boolean soldOut) {
		this.soldOut = soldOut;
	}

	public List<OrderDetailBean> getOrderDetail() {
		return orderDetail;
	}

	public void setOrderDetail(List<OrderDetailBean> orderDetail) {
		this.orderDetail = orderDetail;
	}

	public List<ShoppingCartBean> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCartBean> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<MenuCustomizationBean> getMenuCustomization() {
		return menuCustomization;
	}

	public void setMenuCustomization(List<MenuCustomizationBean> menuCustomization) {
		this.menuCustomization = menuCustomization;
	}

}
