package com.projectdemo.shop.bean;

import java.time.LocalDateTime;
import java.util.List;

public class ShopDTO {

	private Integer id;
	private String name;
	private String account;
	private String password;
	private String email;
	private String phone;
	private String photo; //Base64
	private String district;
	private String address;
	private String latitude;
	private String longitude;
	private Integer review;
	private String bank;
	private boolean openStatus;
	private LocalDateTime cdate;
	private LocalDateTime udate;
	private List<Integer> prepTimeId;
	private List<Integer> shoppingCartId;
	private List<Integer> cannedMessageId;
	private List<Integer> shopCategoryId;
	private List<Integer> holidayId;
	private List<Integer> menuId;
	private List<Integer> orderListId;
	private List<Integer> shopHistoryMessageId;
	private List<Integer> favoritesId;
	private Integer openhrId;
	
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
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
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
	public List<Integer> getPrepTimeId() {
		return prepTimeId;
	}
	public void setPrepTimeId(List<Integer> prepTimeId) {
		this.prepTimeId = prepTimeId;
	}
	public List<Integer> getShoppingCartId() {
		return shoppingCartId;
	}
	public void setShoppingCartId(List<Integer> shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public List<Integer> getCannedMessageId() {
		return cannedMessageId;
	}
	public void setCannedMessageId(List<Integer> cannedMessageId) {
		this.cannedMessageId = cannedMessageId;
	}
	public List<Integer> getShopCategoryId() {
		return shopCategoryId;
	}
	public void setShopCategoryId(List<Integer> shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public List<Integer> getHolidayId() {
		return holidayId;
	}
	public void setHolidayId(List<Integer> holidayId) {
		this.holidayId = holidayId;
	}
	public List<Integer> getMenuId() {
		return menuId;
	}
	public void setMenuId(List<Integer> menuId) {
		this.menuId = menuId;
	}
	public List<Integer> getOrderListId() {
		return orderListId;
	}
	public void setOrderListId(List<Integer> orderListId) {
		this.orderListId = orderListId;
	}
	public List<Integer> getShopHistoryMessageId() {
		return shopHistoryMessageId;
	}
	public void setShopHistoryMessageId(List<Integer> shopHistoryMessageId) {
		this.shopHistoryMessageId = shopHistoryMessageId;
	}
	public List<Integer> getFavoritesId() {
		return favoritesId;
	}
	public void setFavoritesId(List<Integer> favoritesId) {
		this.favoritesId = favoritesId;
	}
	public Integer getOpenhrId() {
		return openhrId;
	}
	public void setOpenhrId(Integer openhrId) {
		this.openhrId = openhrId;
	}
}