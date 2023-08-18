package com.projectdemo.model.bean.shop;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "open_hr")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class OpenHrBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fk_shop_id", nullable = false)
	private ShopBean shop;

	@Column(name = "mon_open", columnDefinition = "time")
	private LocalTime MonOpen;

	@Column(name = "mon_close", columnDefinition = "time")
	private LocalTime MonClose;
	
	@Column(name = "tue_open", columnDefinition = "time")
	private LocalTime tueOpen;

	@Column(name = "tue_close", columnDefinition = "time")
	private LocalTime tueClose;

	@Column(name = "wed_open", columnDefinition = "time")
	private LocalTime wedOpen;

	@Column(name = "wed_close", columnDefinition = "time")
	private LocalTime wedClose;

	@Column(name = "thr_open", columnDefinition = "time")
	private LocalTime thrOpen;

	@Column(name = "thr_close", columnDefinition = "time")
	private LocalTime thrClose;

	@Column(name = "fri_open", columnDefinition = "time")
	private LocalTime friOpen;

	@Column(name = "fri_close", columnDefinition = "time")
	private LocalTime friClose;

	@Column(name = "sat_open", columnDefinition = "time")
	private LocalTime satOpen;

	@Column(name = "sat_close", columnDefinition = "time")
	private LocalTime satClose;

	@Column(name = "sun_open", columnDefinition = "time")
	private LocalTime sunOpen;

	@Column(name = "sun_close", columnDefinition = "time")
	private LocalTime sunClose;


}
