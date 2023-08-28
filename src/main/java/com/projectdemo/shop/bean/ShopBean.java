package com.projectdemo.shop.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projectdemo.customer.bean.FavoritesBean;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.manage.bean.ShopHistoryMessageBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.order.bean.OrderListBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "shop")
public class ShopBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@Column(name = "name", columnDefinition = "nvarchar(50)")
	private String name;

	@Column(name = "account", columnDefinition = "varchar(20)")
	private String account;

	@Column(name = "password", columnDefinition = "varchar(20)")
	private String password;

	@Column(name = "email", columnDefinition = "varchar(20)")
	private String email;

	@Column(name = "phone", columnDefinition = "varchar(10)")
	private String phone;

	@Column(name = "photo", columnDefinition = "varbinary(max)")
	private byte[] photo;

	@Column(name = "district", columnDefinition = "nvarchar(50)")
	private String district;
	
	@Column(name = "address", columnDefinition = "nvarchar(50)")
	private String address;

	@Column(name = "latitude" , columnDefinition = "nvarchar(50)")
	private String latitude;

	@Column(name = "longitude", columnDefinition = "nvarchar(50)")
    private String longitude;


	@Column(name = "review", columnDefinition = "int")
    private Integer review;


	@Column(name = "bank", columnDefinition = "nvarchar(50)")
    private String bank;

	@Column(name = "open_status", columnDefinition = "bit")
    private boolean openStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdate", columnDefinition = "datetime")
	private LocalDateTime cdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "udate", columnDefinition = "datetime")
	private LocalDateTime udate;
	
	//還有一堆關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<PrepTimeBean> prepTime;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<ShoppingCartBean> shoppingCart;
	
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<CannedMessageBean> cannedMessage;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<ShopCategoryBean> shopCategory;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<HolidayBean> holiday;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<MenuBean> menu;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<OrderListBean> orderList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<ShopHistoryMessageBean> shopHistoryMessage;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	@JsonIgnore
	private List<FavoritesBean> favorites;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "shop")
	private OpenHrBean openhrBean;
	
	@PrePersist
	public void onCreate() {
		if(cdate == null) {
			cdate = LocalDateTime.now();
			udate = LocalDateTime.now();
		}
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getDistrict() {
		return district;
	}

	public void setDistrict(String district) {
		this.district = district;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public Integer getReview() {
		return review;
	}

	public void setReview(Integer review) {
		this.review = review;
	}

	public String getBank() {
		return bank;
	}

	public void setBank(String bank) {
		this.bank = bank;
	}

	public boolean isOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(boolean openStatus) {
		this.openStatus = openStatus;
	}

	public LocalDateTime getCdate() {
		return cdate;
	}

	public void setCdate(LocalDateTime cdate) {
		this.cdate = cdate;
	}

	public LocalDateTime getUdate() {
		return udate;
	}

	public void setUdate(LocalDateTime udate) {
		this.udate = udate;
	}

	public List<PrepTimeBean> getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(List<PrepTimeBean> prepTime) {
		this.prepTime = prepTime;
	}

	public List<ShoppingCartBean> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCartBean> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<CannedMessageBean> getCannedMessage() {
		return cannedMessage;
	}

	public void setCannedMessage(List<CannedMessageBean> cannedMessage) {
		this.cannedMessage = cannedMessage;
	}

	public List<ShopCategoryBean> getShopCategory() {
		return shopCategory;
	}

	public void setShopCategory(List<ShopCategoryBean> shopCategory) {
		this.shopCategory = shopCategory;
	}

	public List<HolidayBean> getHoliday() {
		return holiday;
	}

	public void setHoliday(List<HolidayBean> holiday) {
		this.holiday = holiday;
	}

	public List<MenuBean> getMenu() {
		return menu;
	}

	public void setMenu(List<MenuBean> menu) {
		this.menu = menu;
	}

	public List<OrderListBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderListBean> orderList) {
		this.orderList = orderList;
	}

	public List<ShopHistoryMessageBean> getShopHistoryMessage() {
		return shopHistoryMessage;
	}

	public void setShopHistoryMessage(List<ShopHistoryMessageBean> shopHistoryMessage) {
		this.shopHistoryMessage = shopHistoryMessage;
	}

	public List<FavoritesBean> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<FavoritesBean> favorites) {
		this.favorites = favorites;
	}

	public OpenHrBean getOpenhrBean() {
		return openhrBean;
	}

	public void setOpenhrBean(OpenHrBean openhrBean) {
		this.openhrBean = openhrBean;
	}
	
	
	
	
}
