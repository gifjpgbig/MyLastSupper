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

	@Column(name = "money_range", columnDefinition = "int")
	private Integer moneyRange;

	@Column(name = "score_range", columnDefinition = "int")
	private Integer scoreRange;

	@Column(name = "message_send_time", columnDefinition = "time")
	private LocalTime messageSendTime;

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

	public Integer getMoneyRange() {
		return moneyRange;
	}

	public void setMoneyRange(Integer moneyRange) {
		this.moneyRange = moneyRange;
	}

	public Integer getScoreRange() {
		return scoreRange;
	}

	public void setScoreRange(Integer scoreRange) {
		this.scoreRange = scoreRange;
	}

	public LocalTime getMessageSendTime() {
		return messageSendTime;
	}

	public void setMessageSendTime(LocalTime messageSendTime) {
		this.messageSendTime = messageSendTime;
	}

	public String getMessageText() {
		return messageText;
	}

	public void setMessageText(String messageText) {
		this.messageText = messageText;
	}

}
