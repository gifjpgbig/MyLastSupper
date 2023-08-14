package com.projectdemo.model.bean.deliver;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transportation")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class TransportationBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_deliverer_id")
	private DelivererBean deliverer;
	
	@Column(name = "type", columnDefinition = "nvarchar(20)")
	private String type;

	@Column(name = "brand", columnDefinition = "nvarchar(20)")
	private String brand;

	@Column(name = "license", columnDefinition = "varchar(8)")
	private String license;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DelivererBean getDeliverer() {
		return deliverer;
	}

	public void setDeliverer(DelivererBean deliverer) {
		this.deliverer = deliverer;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getLicense() {
		return license;
	}

	public void setLicense(String license) {
		this.license = license;
	}
	
	
	
}
