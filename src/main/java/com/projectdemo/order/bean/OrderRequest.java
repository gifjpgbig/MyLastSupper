package com.projectdemo.order.bean;

import java.util.List;

public class OrderRequest {
    private List<OrderDetailBean> selectedProducts;
    private String address;
    private double totalPrice;
    private String customerID;
    private String shopID;
    private double discount;
    private double deliveryFee;
	public List<OrderDetailBean> getSelectedProducts() {
		return selectedProducts;
	}
	public void setSelectedProducts(List<OrderDetailBean> selectedProducts) {
		this.selectedProducts = selectedProducts;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public String getCustomerID() {
		return customerID;
	}
	public void setCustomerID(String customerID) {
		this.customerID = customerID;
	}
	public String getShopID() {
		return shopID;
	}
	public void setShopID(String shopID) {
		this.shopID = shopID;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}
	public double getDeliveryFee() {
		return deliveryFee;
	}
	public void setDeliveryFee(double deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

    // 省略 getter 和 setter
    
}