package com.projectdemo.order.bean;


public class OrderRequest {
	private Integer amount;
	private Integer total_price;
	private String customization;
	private Integer fk_shop_id;
	private String dish_name;
	private Integer dish_price;
	private Integer id;
	private String shop_name;
	public Integer getAmount() {
		return amount;
	}
	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	
	
	public Integer getTotal_price() {
		return total_price;
	}
	public void setTotal_price(Integer total_price) {
		this.total_price = total_price;
	}
	public String getCustomization() {
		return customization;
	}
	public void setCustomization(String customization) {
		this.customization = customization;
	}
	public Integer getFk_shop_id() {
		return fk_shop_id;
	}
	public void setFk_shop_id(Integer fk_shop_id) {
		this.fk_shop_id = fk_shop_id;
	}
	public String getDish_name() {
		return dish_name;
	}
	public void setDish_name(String dish_name) {
		this.dish_name = dish_name;
	}
	public Integer getDish_price() {
		return dish_price;
	}
	public void setDish_price(Integer dish_price) {
		this.dish_price = dish_price;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getShop_name() {
		return shop_name;
	}
	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}
	@Override
	public String toString() {
		return "OrderRequest [amount=" + amount + ", total_price=" + total_price + ", customization=" + customization
				+ ", fk_shop_id=" + fk_shop_id + ", dish_name=" + dish_name + ", dish_price=" + dish_price + ", id="
				+ id + ", shop_name=" + shop_name + "]";
	}

	
	
       
}