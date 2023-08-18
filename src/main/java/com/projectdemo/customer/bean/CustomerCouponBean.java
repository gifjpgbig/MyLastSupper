package com.projectdemo.customer.bean;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "customer_coupon")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CustomerCouponBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_customer_id", columnDefinition = "int")
	private CustomerBean customer;
	
	@ManyToOne
	@JoinColumn(name = "fk_coupon_id", columnDefinition = "int")
	private CouponBean coupon;

	@Column(name = "description", columnDefinition = "nvarchar(max)")
	private String description;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", columnDefinition = "datetime")
	private LocalDateTime startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "continued_date", columnDefinition = "datetime")
	private LocalDateTime continuedDate;
	
	//優惠卷使用次數上限
	@Column(name = "usage_limit", columnDefinition = "int")
	private Integer usageLimit;

	//優惠卷使用次數計算
	@Column(name = "usage_count", columnDefinition = "int")
	private Integer usageCount;

	@Column(name = "minimum_order_amount", columnDefinition = "nvarchar(50)")
	private String minimumOrderAmount;

	@Column(name = "shop_type", columnDefinition = "nvarchar(50)")
	private String shopType;


	@Column(name = "status", columnDefinition = "nvarchar(50)")
	private String status;
	
	//一個使用者能使用的上限
	@Column(name = "user_usage_limit", columnDefinition = "int")
	private Integer userUsageLimit;
	
	//優惠卷商標
	@Column(name = "icon", columnDefinition = "varbinary(max)")
	private byte[] icon;
	
	//優惠卷商標描述
	@Column(name = "icon_description", columnDefinition = "nvarchar(max)")
	private String iconDecription;

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

	public CouponBean getCoupon() {
		return coupon;
	}

	public void setCoupon(CouponBean coupon) {
		this.coupon = coupon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getContinuedDate() {
		return continuedDate;
	}

	public void setContinuedDate(LocalDateTime continuedDate) {
		this.continuedDate = continuedDate;
	}

	public Integer getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(Integer usageLimit) {
		this.usageLimit = usageLimit;
	}

	public Integer getUsageCount() {
		return usageCount;
	}

	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
	}

	public String getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	public void setMinimumOrderAmount(String minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	public String getShopType() {
		return shopType;
	}

	public void setShopType(String shopType) {
		this.shopType = shopType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUserUsageLimit() {
		return userUsageLimit;
	}

	public void setUserUsageLimit(Integer userUsageLimit) {
		this.userUsageLimit = userUsageLimit;
	}

	public byte[] getIcon() {
		return icon;
	}

	public void setIcon(byte[] icon) {
		this.icon = icon;
	}

	public String getIconDecription() {
		return iconDecription;
	}

	public void setIconDecription(String iconDecription) {
		this.iconDecription = iconDecription;
	}

	
	
	
}