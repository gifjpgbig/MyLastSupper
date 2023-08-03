package com.projectdemo.customer.bean;

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
@Table(name = "user_invite_code")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class UserInviteCodeBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	
	@OneToOne()
	@JoinColumn(name = "fk_customer_id", columnDefinition = "int")
	private CustomerBean customer;
	
	@Column(name = "code",columnDefinition = "nvarchar(50)")
	private String code;
	
	@Column(name = "amount" , columnDefinition  = "int")
	private Integer amount;
	
	@Column(name = "invite_quota" , columnDefinition = "int")
	private Integer inviteQuota;
	
	@Column(name = "invited_quota" , columnDefinition = "int")
	private Integer invitedQuota;

	
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getAmount() {
		return amount;
	}

	public void setAmount(Integer amount) {
		this.amount = amount;
	}

	public Integer getInviteQuota() {
		return inviteQuota;
	}

	public void setInviteQuota(Integer inviteQuota) {
		this.inviteQuota = inviteQuota;
	}

	public Integer getInvitedQuota() {
		return invitedQuota;
	}

	public void setInvitedQuota(Integer invitedQuota) {
		this.invitedQuota = invitedQuota;
	}


}
