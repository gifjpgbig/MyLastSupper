package com.projectdemo.customer.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "address")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class AddressBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "fk_customer_id", columnDefinition = "int")
	private CustomerBean customer;

	@Column(name = "location", columnDefinition = "nvarchar(50)")
	private String location;

	@Column(name = "longitude", columnDefinition = "nvarchar(50)")
	private String longitude;

	@Column(name = "latitude", columnDefinition = "nvarchar(50)")
	private String latitude;

	@Column(name = "delivery_option", columnDefinition = "nvarchar(50)")
	private String deliveryOption;

	@Column(name = "building", columnDefinition = "nvarchar(50)")
	private String building;

	@Column(name = "delivery_instructions", columnDefinition = "nvarchar(50)")
	private String deliveryInstructions;

	@Column(name = "floor_No", columnDefinition = "nvarchar(50)")
	private String floorNo;

	
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getDeliveryOption() {
		return deliveryOption;
	}

	public void setDeliveryOption(String deliveryOption) {
		this.deliveryOption = deliveryOption;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getDeliveryInstructions() {
		return deliveryInstructions;
	}

	public void setDeliveryInstructions(String deliveryInstructions) {
		this.deliveryInstructions = deliveryInstructions;
	}

	public String getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(String floorNo) {
		this.floorNo = floorNo;
	}
	
	
	
}