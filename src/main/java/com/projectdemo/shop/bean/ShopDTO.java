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
	private byte[] photo;
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
	
	public String getAccount() {
		return account;
	}
	public String getAddress() {
		return address;
	}
	public String getBank() {
		return bank;
	}
	public List<Integer> getCannedMessageId() {
		return cannedMessageId;
	}
	public LocalDateTime getCdate() {
		return cdate;
	}
	public String getDistrict() {
		return district;
	}
	public String getEmail() {
		return email;
	}
	public List<Integer> getFavoritesId() {
		return favoritesId;
	}
	public List<Integer> getHolidayId() {
		return holidayId;
	}
	public Integer getId() {
		return id;
	}
	public String getLatitude() {
		return latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public List<Integer> getMenuId() {
		return menuId;
	}
	public String getName() {
		return name;
	}
	public Integer getOpenhrId() {
		return openhrId;
	}
	public List<Integer> getOrderListId() {
		return orderListId;
	}
	public String getPassword() {
		return password;
	}
	public String getPhone() {
		return phone;
	}
	public byte[] getPhoto() {
		return photo;
	}
	public List<Integer> getPrepTimeId() {
		return prepTimeId;
	}
	public Integer getReview() {
		return review;
	}
	public List<Integer> getShopCategoryId() {
		return shopCategoryId;
	}
	public List<Integer> getShopHistoryMessageId() {
		return shopHistoryMessageId;
	}
	public List<Integer> getShoppingCartId() {
		return shoppingCartId;
	}
	public LocalDateTime getUdate() {
		return udate;
	}
	public boolean isOpenStatus() {
		return openStatus;
	}
	public void setAccount(String account) {
		this.account = account;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public void setBank(String bank) {
		this.bank = bank;
	}
	public void setCannedMessageId(List<Integer> cannedMessageId) {
		this.cannedMessageId = cannedMessageId;
	}
	public void setCdate(LocalDateTime cdate) {
		this.cdate = cdate;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public void setFavoritesId(List<Integer> favoritesId) {
		this.favoritesId = favoritesId;
	}
	public void setHolidayId(List<Integer> holidayId) {
		this.holidayId = holidayId;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public void setMenuId(List<Integer> menuId) {
		this.menuId = menuId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setOpenhrId(Integer openhrId) {
		this.openhrId = openhrId;
	}
	public void setOpenStatus(boolean openStatus) {
		this.openStatus = openStatus;
	}
	public void setOrderListId(List<Integer> orderListId) {
		this.orderListId = orderListId;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}
	public void setPrepTimeId(List<Integer> prepTimeId) {
		this.prepTimeId = prepTimeId;
	}
	public void setReview(Integer review) {
		this.review = review;
	}
	public void setShopCategoryId(List<Integer> shopCategoryId) {
		this.shopCategoryId = shopCategoryId;
	}
	public void setShopHistoryMessageId(List<Integer> shopHistoryMessageId) {
		this.shopHistoryMessageId = shopHistoryMessageId;
	}
	public void setShoppingCartId(List<Integer> shoppingCartId) {
		this.shoppingCartId = shoppingCartId;
	}
	public void setUdate(LocalDateTime udate) {
		this.udate = udate;
	}
}