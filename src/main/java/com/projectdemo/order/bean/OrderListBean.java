package com.projectdemo.order.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.customer.bean.CustomerBean;
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
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "order_list")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class OrderListBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_customer_id", columnDefinition = "int")
	private CustomerBean customer;
	
	@ManyToOne
	@JoinColumn(name = "fk_shop_id", columnDefinition = "int")
	private ShopBean shop;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderList")
	@JsonIgnore
	private List<OrderDetailBean> orderDetails;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "orderList")
	@JsonIgnore
	private List<DeliverDetailBean> deliverDetail;
	
	@Column(name = "total_price", columnDefinition = "int")
	private Integer totalPrice;

	@Column(name = "cus_status", columnDefinition = "nvarchar(10)")
	private String cusStatus;	

	@Column(name = "shop_status", columnDefinition = "nvarchar(10)")
	private String shopStatus;	
	
	@Column(name = "deliver_status", columnDefinition = "nvarchar(10)")
	private String deliverStatus;	
	
	@Column(name = "address", columnDefinition = "nvarchar(50)")
	private String address;

	@Column(name = "delivery_fee", columnDefinition = "int")
	private Integer deliveryFee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_time", columnDefinition = "datetime")
	private LocalDateTime orderTime;

	@Column(name = "discount", columnDefinition = "int")
	private Integer discount;

	@Column(name = "shop_review", columnDefinition = "int")
	private Integer shopReview;

	@Column(name = "shop_comments", columnDefinition = "nvarchar(200)")
	private String shopComments;

	@Column(name = "dish_comments", columnDefinition = "nvarchar(200)")
	private String dishComments;

	@Column(name = "shop_feedback_reply", columnDefinition = "nvarchar(200)")
	private String shopFeedbackReply;

	public String getAddress() {
		return address;
	}

	public String getCusStatus() {
		return cusStatus;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public List<DeliverDetailBean> getDeliverDetail() {
		return deliverDetail;
	}

	public String getDeliverStatus() {
		return deliverStatus;
	}

	public Integer getDeliveryFee() {
		return deliveryFee;
	}

	public Integer getDiscount() {
		return discount;
	}

	public String getDishComments() {
		return dishComments;
	}


	public Integer getId() {
		return id;
	}

	public List<OrderDetailBean> getOrderDetails() {
		return orderDetails;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public ShopBean getShop() {
		return shop;
	}

	

	public String getShopComments() {
		return shopComments;
	}

	public String getShopFeedbackReply() {
		return shopFeedbackReply;
	}

	public Integer getShopReview() {
		return shopReview;
	}

	public String getShopStatus() {
		return shopStatus;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setCusStatus(String cusStatus) {
		this.cusStatus = cusStatus;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public void setDeliverDetail(List<DeliverDetailBean> deliverDetail) {
		this.deliverDetail = deliverDetail;
	}

	public void setDeliverStatus(String deliverStatus) {
		this.deliverStatus = deliverStatus;
	}

	public void setDeliveryFee(Integer deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public void setDishComments(String dishComments) {
		this.dishComments = dishComments;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderDetails(List<OrderDetailBean> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public void setShop(ShopBean shop) {
		this.shop = shop;
	}

	public void setShopComments(String shopComments) {
		this.shopComments = shopComments;
	}

	public void setShopFeedbackReply(String shopFeedbackReply) {
		this.shopFeedbackReply = shopFeedbackReply;
	}

	public void setShopReview(Integer shopReview) {
		this.shopReview = shopReview;
	}

	public void setShopStatus(String shopStatus) {
		this.shopStatus = shopStatus;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	@Override
	public String toString() {
		return "OrderListBean [id=" + id + ", customer=" + customer + ", shop=" + shop + ", totalPrice=" + totalPrice
				+  ", address=" + address + ", deliveryFee=" + deliveryFee + ", orderTime="
				+ orderTime + ", discount=" + discount + ", shopReview=" + shopReview + ", shopComments=" + shopComments
				+ ", dishComments=" + dishComments + ", shopFeedbackReply=" + shopFeedbackReply + "]";
	}


	
}
