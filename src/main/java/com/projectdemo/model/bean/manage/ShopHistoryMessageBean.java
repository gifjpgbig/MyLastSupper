package com.projectdemo.model.bean.manage;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.model.bean.shop.ShopBean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "shop_history_message")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class ShopHistoryMessageBean {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_shop_id" ,columnDefinition = "int")
	private ShopBean shop;
	
	@ManyToOne
	@JoinColumn(name = "fk_customer_service_id" ,columnDefinition = "int")
	private CustomerServiceBean customerService;

	@Column(name = "message_text" , columnDefinition = "TEXT")
	private String MessageText;

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

	public CustomerServiceBean getCustomerService() {
		return customerService;
	}

	public void setCustomerService(CustomerServiceBean customerService) {
		this.customerService = customerService;
	}

	public String getMessageText() {
		return MessageText;
	}

	public void setMessageText(String messageText) {
		MessageText = messageText;
	}
	
	
}
