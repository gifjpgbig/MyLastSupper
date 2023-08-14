package com.projectdemo.menu.bean;

import java.time.LocalTime;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "menu_hr")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class MenuHrBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@OneToOne
	@JoinColumn(name = "fk_menu_id", nullable = false)
	private MenuBean menu;

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

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MenuBean getMenu() {
		return menu;
	}

	public void setMenu(MenuBean menu) {
		this.menu = menu;
	}

	public LocalTime getMonOpen() {
		return MonOpen;
	}

	public void setMonOpen(LocalTime monOpen) {
		MonOpen = monOpen;
	}

	public LocalTime getMonClose() {
		return MonClose;
	}

	public void setMonClose(LocalTime monClose) {
		MonClose = monClose;
	}

	public LocalTime getTueOpen() {
		return tueOpen;
	}

	public void setTueOpen(LocalTime tueOpen) {
		this.tueOpen = tueOpen;
	}

	public LocalTime getTueClose() {
		return tueClose;
	}

	public void setTueClose(LocalTime tueClose) {
		this.tueClose = tueClose;
	}

	public LocalTime getWedOpen() {
		return wedOpen;
	}

	public void setWedOpen(LocalTime wedOpen) {
		this.wedOpen = wedOpen;
	}

	public LocalTime getWedClose() {
		return wedClose;
	}

	public void setWedClose(LocalTime wedClose) {
		this.wedClose = wedClose;
	}

	public LocalTime getThrOpen() {
		return thrOpen;
	}

	public void setThrOpen(LocalTime thrOpen) {
		this.thrOpen = thrOpen;
	}

	public LocalTime getThrClose() {
		return thrClose;
	}

	public void setThrClose(LocalTime thrClose) {
		this.thrClose = thrClose;
	}

	public LocalTime getFriOpen() {
		return friOpen;
	}

	public void setFriOpen(LocalTime friOpen) {
		this.friOpen = friOpen;
	}

	public LocalTime getFriClose() {
		return friClose;
	}

	public void setFriClose(LocalTime friClose) {
		this.friClose = friClose;
	}

	public LocalTime getSatOpen() {
		return satOpen;
	}

	public void setSatOpen(LocalTime satOpen) {
		this.satOpen = satOpen;
	}

	public LocalTime getSatClose() {
		return satClose;
	}

	public void setSatClose(LocalTime satClose) {
		this.satClose = satClose;
	}

	public LocalTime getSunOpen() {
		return sunOpen;
	}

	public void setSunOpen(LocalTime sunOpen) {
		this.sunOpen = sunOpen;
	}

	public LocalTime getSunClose() {
		return sunClose;
	}

	public void setSunClose(LocalTime sunClose) {
		this.sunClose = sunClose;
	}

}
