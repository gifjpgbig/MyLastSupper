package com.projectdemo.customer.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.order.bean.OrderListBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
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
@Table(name = "customer")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CustomerBean {

	// 客戶id
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer customerID;

	// 客戶名稱
	@Column(name = "name", columnDefinition = "nvarchar(50)")
	private String name;

	// 客戶帳號
	@Column(name = "account", columnDefinition = "nvarchar(50)",unique = true)
	private String account;

	// 客戶密碼
	@Column(name = "password", columnDefinition = "nvarchar(50)")
	private String password;

	// 客戶郵件地址
	@Column(name = "email", columnDefinition = "nvarchar(50)")
	private String email;

	// 客戶電話
	@Column(name = "phone", columnDefinition = "nvarchar(50)")
	private String phone;

	// 客戶生日
	@Column(name = "birthday", columnDefinition = "Date")
	private LocalDate birthday;

	// 客戶大頭貼
	@Column(name = "photo", columnDefinition = "varbinary(max)")
	private byte[] photo;

	@Column(name = "payment", columnDefinition = "nvarchar(MAX)")
	private String payment;

	// 創建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	// 最新更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", columnDefinition = "datetime")
	private LocalDateTime updateDate;

	// 是否為首購客戶
	@Column(name = "is_first_buy", columnDefinition = "bit")
	private boolean isFirstBuy;

	@OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnore
	private List<AddressBean> address;

	@OneToOne(mappedBy = "customer")
	@JsonIgnore
	private UserInviteCodeBean userInvite;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnore
	private List<CustomerCouponBean> customerCoupon;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnore
	private List<ShoppingCartBean> shoppingCart;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnore
	private List<OrderListBean> orderList;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	@JsonIgnore
	private List<FavoritesBean> favorites;

	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		} 
		
	}
	

	public Integer getCustomerID() {
		return customerID;
	}

	public void setCustomerID(Integer customerID) {
		this.customerID = customerID;
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

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
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

	public boolean isFirstBuy() {
		return isFirstBuy;
	}

	public void setFirstBuy(boolean isFirstBuy) {
		this.isFirstBuy = isFirstBuy;
	}

	public List<AddressBean> getAddress() {
		return address;
	}

	public void setAddress(List<AddressBean> address) {
		this.address = address;
	}

	public UserInviteCodeBean getUserInvite() {
		return userInvite;
	}

	public void setUserInvite(UserInviteCodeBean userInvite) {
		this.userInvite = userInvite;
	}

	public List<CustomerCouponBean> getCustomerCoupon() {
		return customerCoupon;
	}

	public void setCustomerCoupon(List<CustomerCouponBean> customerCoupon) {
		this.customerCoupon = customerCoupon;
	}

	public List<ShoppingCartBean> getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(List<ShoppingCartBean> shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

	public List<OrderListBean> getOrderList() {
		return orderList;
	}

	public void setOrderList(List<OrderListBean> orderList) {
		this.orderList = orderList;
	}

	public List<FavoritesBean> getFavorites() {
		return favorites;
	}

	public void setFavorites(List<FavoritesBean> favorites) {
		this.favorites = favorites;
	}

}
