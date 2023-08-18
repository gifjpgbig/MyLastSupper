package com.projectdemo.menu.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.shop.bean.ShopBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "menu")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class MenuBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fk_shop_id", columnDefinition = "int")
	private ShopBean shop;

	@Column(name = "supply", columnDefinition = "bit")
	private boolean Supply;

	@Column(name = "name", columnDefinition = "nvarchar(MAX)")
	private String name;

	// 創建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	// 最新更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", columnDefinition = "datetime")
	private LocalDateTime updateDate;

	@PreUpdate
	public void onUpdate() {
		updateDate = LocalDateTime.now();
	}

	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		}
	}

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "menu")
	@JsonIgnore
	private MenuHrBean MenuHr;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
	@JsonIgnore
	private List<DishBean> dish;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menu")
	@JsonIgnore
	private List<MenuCategoryBean> menuCategory;

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public MenuHrBean getMenuHr() {
		return MenuHr;
	}

	public void setMenuHr(MenuHrBean menuHr) {
		MenuHr = menuHr;
	}

	public List<DishBean> getDish() {
		return dish;
	}

	public void setDish(List<DishBean> dish) {
		this.dish = dish;
	}

	public boolean isSupply() {
		return Supply;
	}

	public void setSupply(boolean supply) {
		Supply = supply;
	}

	public List<MenuCategoryBean> getMenuCategory() {
		return menuCategory;
	}

	public void setMenuCategory(List<MenuCategoryBean> menuCategory) {
		this.menuCategory = menuCategory;
	}

	
}