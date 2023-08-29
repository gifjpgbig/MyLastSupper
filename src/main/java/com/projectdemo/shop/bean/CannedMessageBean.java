package com.projectdemo.shop.bean;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "canned_message")
public class CannedMessageBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@ManyToOne
	@JoinColumn(name = "fk_shop_id", nullable = false)
	private ShopBean shop;

	@Column(name = "message_cdate", columnDefinition = "datetime")
	private LocalDateTime messageCDate;

	@Column(name = "message_udate", columnDefinition = "datetime")
	private LocalDateTime messageUDate;

//	@Column(name = "money_range", columnDefinition = "nvarchar(max)")
//	private String moneyRange;
	
	@Column(name = "money_start", columnDefinition = "int")
	private Integer moneyStart;
	
	@Column(name = "money_end", columnDefinition = "int")
	private Integer moneyEnd;

//	@Column(name = "score_range", columnDefinition = "nvarchar(max)")
//	private String scoreRange;
	
	@Column(name = "score_start", columnDefinition = "int")
	private Integer scoreStart;
	
	@Column(name = "score_end", columnDefinition = "int")
	private Integer scoreEnd;

//	@Column(name = "message_send_time", columnDefinition = "nvarchar(max)")
//	private String messageSendTime;
	
	@Column(name = "time_start", columnDefinition = "time")
	private LocalTime timeStart;
	
	@Column(name = "time_end", columnDefinition = "time")
	private LocalTime timeEnd;

	@Column(name = "message_text", columnDefinition = "nvarchar(max)")
	private String messageText;

	@PrePersist
	public void onCreate() {
		if (messageCDate == null) {
			messageCDate = messageUDate = LocalDateTime.now();
		}
	}

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

	public LocalDateTime getMessageCDate() {
		return messageCDate;
	}

	public void setMessageCDate(LocalDateTime messageCDate) {
		this.messageCDate = messageCDate;
	}

	public LocalDateTime getMessageUDate() {
		return messageUDate;
	}

	public void setMessageUDate(LocalDateTime messageUDate) {
		this.messageUDate = messageUDate;
	}

	public Integer getMoneyStart() {
		return moneyStart;
	}

	public void setMoneyStart(Integer moneyStart) {
		this.moneyStart = moneyStart;
	}

	public Integer getMoneyEnd() {
		return moneyEnd;
	}

	public void setMoneyEnd(Integer moneyEnd) {
		this.moneyEnd = moneyEnd;
	}

	public Integer getScoreStart() {
		return scoreStart;
	}

	public void setScoreStart(Integer scoreStart) {
		this.scoreStart = scoreStart;
	}

	public Integer getScoreEnd() {
		return scoreEnd;
	}

	public void setScoreEnd(Integer scoreEnd) {
		this.scoreEnd = scoreEnd;
	}

	public LocalTime getTimeStart() {
		return timeStart;
	}

	public void setTimeStart(LocalTime timeStart) {
		this.timeStart = timeStart;
	}

	public LocalTime getTimeEnd() {
		return timeEnd;
	}

	public void setTimeEnd(LocalTime timeEnd) {
		this.timeEnd = timeEnd;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}
}
