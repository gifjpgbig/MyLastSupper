package com.projectdemo.order.bean;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.deliver.bean.DelivererBean;

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
@Table(name = "deliver_detail")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DeliverDetailBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fk_order_list_id", nullable = false, columnDefinition = "int")
	private OrderListBean orderList;

	@ManyToOne
	@JoinColumn(name = "fk_deliverer_id", nullable = false, columnDefinition = "int")
	private DelivererBean deliverer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "deliver_time", columnDefinition = "datetime")
	private LocalDateTime deliverTime;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "arrive_time", columnDefinition = "datetime")
	private LocalDateTime arriveTime;

	@Column(name = "address", columnDefinition = "nvarchar(MAX)")
	private String address;

	@Column(name = "driver_name", columnDefinition = "nvarchar(MAX)")
	private String driverName;

	@Column(name = "cancel_reason", columnDefinition = "nvarchar(MAX)")
	private String cancelReason;
	
	@Column(name = "is_cancel", columnDefinition = "bit")
	private boolean isCancel;
	
	@Column(name = "is_complete", columnDefinition = "bit")
	private boolean isComplete;

	@Column(name = "deliver_review", columnDefinition = "int")
	private Integer deliverReview;

	public String getAddress() {
		return address;
	}

	public LocalDateTime getArriveTime() {
		return arriveTime;
	}
	

	public String getCancelReason() {
		return cancelReason;
	}

	public DelivererBean getDeliverer() {
		return deliverer;
	}

	public Integer getDeliverReview() {
		return deliverReview;
	}

	public LocalDateTime getDeliverTime() {
		return deliverTime;
	}

	public String getDriverName() {
		return driverName;
	}

	public Integer getId() {
		return id;
	}



	public OrderListBean getOrderList() {
		return orderList;
	}

	public boolean isCancel() {
		return isCancel;
	}

	public boolean isComplete() {
		return isComplete;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public void setArriveTime(LocalDateTime arriveTime) {
		this.arriveTime = arriveTime;
	}

	public void setCancel(boolean isCancel) {
		this.isCancel = isCancel;
	}

	public void setCancelReason(String cancelReason) {
		this.cancelReason = cancelReason;
	}

	public void setComplete(boolean isComplete) {
		this.isComplete = isComplete;
	}

	public void setDeliverer(DelivererBean deliverer) {
		this.deliverer = deliverer;
	}

	public void setDeliverReview(Integer deliverReview) {
		this.deliverReview = deliverReview;
	}

	public void setDeliverTime(LocalDateTime deliverTime) {
		this.deliverTime = deliverTime;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrderList(OrderListBean orderList) {
		this.orderList = orderList;
	}

}
