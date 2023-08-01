package com.projectdemo.customer.bean;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "coupon")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CouponBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "coupon")
	@JsonIgnore
	private List<CustomerCouponBean> cusomterCoupon;

	@Column(name = "coupon_code", columnDefinition = "nvarchar(max)")
	private String couponCode;

	@Column(name = "description", columnDefinition = "nvarchar(max)")
	private String description;

	@Column(name = "discount_amount", columnDefinition = "int")
	private Integer discountAmount;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start_date", columnDefinition = "datetime")
	private LocalDateTime startDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end_date", columnDefinition = "datetime")
	private LocalDateTime endDate;

	// 優惠卷使用次數上限
	@Column(name = "usage_limit", columnDefinition = "int")
	private Integer usageLimit;

	@Column(name = "create_by", columnDefinition = "nvarchar(50)")
	private String createBy;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "created_date", columnDefinition = "datetime")
	private LocalDateTime createdDate;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "last_update_date", columnDefinition = "datetime")
	private LocalDateTime lastUpdateDate;

	@Column(name = "minimum_order_amount", columnDefinition = "nvarchar(50)")
	private String minimumOrderAmount;

	@Column(name = "applicable_products", columnDefinition = "nvarchar(50)")
	private String applicableProducts;

	@Column(name = "usage_count", columnDefinition = "int")
	private Integer usageCount;

	@Column(name = "status", columnDefinition = "nvarchar(50)")
	private String status;

	// 一個使用者能使用的上限
	@Column(name = "user_usage_limit", columnDefinition = "int")
	private Integer userUsageLimit;

	// 優惠卷商標
	@Column(name = "icon", columnDefinition = "varbinary(max)")
	private byte[] icon;

	// 優惠卷商標描述
	@Column(name = "icon_description", columnDefinition = "nvarchar(max)")
	private String iconDecription;

	@PrePersist
	public void onCreate() {
		if (createdDate == null) {
			createdDate = LocalDateTime.now();
		}
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<CustomerCouponBean> getCusomterCoupon() {
		return cusomterCoupon;
	}

	public void setCusomterCoupon(List<CustomerCouponBean> cusomterCoupon) {
		this.cusomterCoupon = cusomterCoupon;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getDiscountAmount() {
		return discountAmount;
	}

	public void setDiscountAmount(Integer discountAmount) {
		this.discountAmount = discountAmount;
	}

	public LocalDateTime getStartDate() {
		return startDate;
	}

	public void setStartDate(LocalDateTime startDate) {
		this.startDate = startDate;
	}

	public LocalDateTime getEndDate() {
		return endDate;
	}

	public void setEndDate(LocalDateTime endDate) {
		this.endDate = endDate;
	}

	public Integer getUsageLimit() {
		return usageLimit;
	}

	public void setUsageLimit(Integer usageLimit) {
		this.usageLimit = usageLimit;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public LocalDateTime getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(LocalDateTime createdDate) {
		this.createdDate = createdDate;
	}

	public LocalDateTime getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getMinimumOrderAmount() {
		return minimumOrderAmount;
	}

	public void setMinimumOrderAmount(String minimumOrderAmount) {
		this.minimumOrderAmount = minimumOrderAmount;
	}

	public String getApplicableProducts() {
		return applicableProducts;
	}

	public void setApplicableProducts(String applicableProducts) {
		this.applicableProducts = applicableProducts;
	}

	public Integer getUsageCount() {
		return usageCount;
	}

	public void setUsageCount(Integer usageCount) {
		this.usageCount = usageCount;
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
