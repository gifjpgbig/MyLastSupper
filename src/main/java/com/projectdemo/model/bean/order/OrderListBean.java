package com.projectdemo.model.bean.order;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.customer.bean.CustomerBean;
import com.projectdemo.model.bean.shop.ShopBean;

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
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
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
	private List<DeliverDetailBean> deliverDetailBean;
	
	@Column(name = "total_price", columnDefinition = "int")
	private Integer totalPrice;

	@Column(name = "status", columnDefinition = "nvarchar(10)")
	private String status;	
	
	@Column(name = "address", columnDefinition = "nvarchar(50)")
	private String address;

	@Column(name = "delivery_fee", columnDefinition = "int")
	private Integer deliveryFee;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "order_time", columnDefinition = "datetime")
	private LocalDateTime orderTime;

	@Column(name = "discount", columnDefinition = "int")
	private Integer discount;

	@Column(name = "shop_review", columnDefinition = "nvarchar(200)")
	private String shopReview;

	@Column(name = "shop_comments", columnDefinition = "nvarchar(200)")
	private String shopComments;

	@Column(name = "dish_comments", columnDefinition = "nvarchar(200)")
	private String dishComments;

	@Column(name = "shop_feedback_reply", columnDefinition = "nvarchar(200)")
	private String shopFeedbackReply;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public CustomerBean getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerBean customer) {
		this.customer = customer;
	}

	public ShopBean getShop() {
		return shop;
	}

	public void setShop(ShopBean shop) {
		this.shop = shop;
	}

	public List<OrderDetailBean> getOrderDetails() {
		return orderDetails;
	}

	public void setOrderDetails(List<OrderDetailBean> orderDetails) {
		this.orderDetails = orderDetails;
	}

	public List<DeliverDetailBean> getDeliverDetailBean() {
		return deliverDetailBean;
	}

	public void setDeliverDetailBean(List<DeliverDetailBean> deliverDetailBean) {
		this.deliverDetailBean = deliverDetailBean;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Integer getDeliveryFee() {
		return deliveryFee;
	}

	public void setDeliveryFee(Integer deliveryFee) {
		this.deliveryFee = deliveryFee;
	}

	public LocalDateTime getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(LocalDateTime orderTime) {
		this.orderTime = orderTime;
	}

	public Integer getDiscount() {
		return discount;
	}

	public void setDiscount(Integer discount) {
		this.discount = discount;
	}

	public String getShopReview() {
		return shopReview;
	}

	public void setShopReview(String shopReview) {
		this.shopReview = shopReview;
	}

	public String getShopComments() {
		return shopComments;
	}

	public void setShopComments(String shopComments) {
		this.shopComments = shopComments;
	}

	public String getDishComments() {
		return dishComments;
	}

	public void setDishComments(String dishComments) {
		this.dishComments = dishComments;
	}

	public String getShopFeedbackReply() {
		return shopFeedbackReply;
	}

	public void setShopFeedbackReply(String shopFeedbackReply) {
		this.shopFeedbackReply = shopFeedbackReply;
	}


	
}
