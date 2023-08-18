package com.projectdemo.order.bean;

import java.io.Serializable;
import java.time.LocalDateTime;

public class OrderProgressDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private String cus_address;
	private LocalDateTime deliver_time;
	private Integer delivery_fee;
	private LocalDateTime order_time;
	private String shop_address;
	private String shop_name;

	public String getCus_address() {
		return cus_address;
	}

	public void setCus_address(String cus_address) {
		this.cus_address = cus_address;
	}

	public LocalDateTime getDeliver_time() {
		return deliver_time;
	}

	public void setDeliver_time(LocalDateTime deliver_time) {
		this.deliver_time = deliver_time;
	}

	public Integer getDelivery_fee() {
		return delivery_fee;
	}

	public void setDelivery_fee(Integer delivery_fee) {
		this.delivery_fee = delivery_fee;
	}

	public LocalDateTime getOrder_time() {
		return order_time;
	}

	public void setOrder_time(LocalDateTime order_time) {
		this.order_time = order_time;
	}

	public String getShop_address() {
		return shop_address;
	}

	public void setShop_address(String shop_address) {
		this.shop_address = shop_address;
	}

	public String getShop_name() {
		return shop_name;
	}

	public void setShop_name(String shop_name) {
		this.shop_name = shop_name;
	}

	@Override
	public String toString() {
		return "OrderProgressDTO [cus_address=" + cus_address + ", deliver_time=" + deliver_time + ", delivery_fee="
				+ delivery_fee + ", order_time=" + order_time + ", shop_address=" + shop_address + ", shop_name="
				+ shop_name + "]";
	}
	
	public OrderProgressDTO() {
		super();
	}

	public OrderProgressDTO(String cus_address, LocalDateTime deliver_time, Integer delivery_fee,
			LocalDateTime order_time, String shop_address, String shop_name) {
		super();
		this.cus_address = cus_address;
		this.deliver_time = deliver_time;
		this.delivery_fee = delivery_fee;
		this.order_time = order_time;
		this.shop_address = shop_address;
		this.shop_name = shop_name;
	}

}