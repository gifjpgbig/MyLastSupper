package com.projectdemo.manage.bean;


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

@Entity
@Table(name = "deliverer_history_message")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DelivererHistoryMessageBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_deliverer_id")
	private DelivererBean deliverer;
	
	@Column(name = "message_text" , columnDefinition = "TEXT")
	private String MessageText;

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

	public String getMessageText() {
		return MessageText;
	}

	public void setMessageText(String messageText) {
		MessageText = messageText;
	}
	
	
}
