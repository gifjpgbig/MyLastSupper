package com.projectdemo.model.bean.shop;

import java.time.LocalDate;
import java.time.LocalTime;

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
@Table(name = "holiday")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class HolidayBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fk_shop_id", nullable = false)
	private ShopBean shop;
	
	@Column(name = "rest_date", columnDefinition = "date")
	private LocalDate restDate;
	
	@Column(name = "whole_day", columnDefinition = "bit")
	private boolean wholeDay;
	
	@Column(name = "start_time", columnDefinition = "time")
	private LocalTime startTime;

	@Column(name = "end_time", columnDefinition = "time")
	private LocalTime endTime;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public ShopBean getShop() {
		return shop;
	}

	public void setShop(ShopBean shop) {
		this.shop = shop;
	}

	public LocalDate getRestDate() {
		return restDate;
	}

	public void setRestDate(LocalDate restDate) {
		this.restDate = restDate;
	}

	public boolean isWholeDay() {
		return wholeDay;
	}

	public void setWholeDay(boolean wholeDay) {
		this.wholeDay = wholeDay;
	}

	public LocalTime getStartTime() {
		return startTime;
	}

	public void setStartTime(LocalTime startTime) {
		this.startTime = startTime;
	}

	public LocalTime getEndTime() {
		return endTime;
	}

	public void setEndTime(LocalTime endTime) {
		this.endTime = endTime;
	}

	
	
}
