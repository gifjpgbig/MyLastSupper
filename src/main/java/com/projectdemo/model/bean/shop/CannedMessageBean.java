package com.projectdemo.model.bean.shop;

import java.time.LocalDateTime;
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
@Table(name = "canned_message")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
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
	
}
