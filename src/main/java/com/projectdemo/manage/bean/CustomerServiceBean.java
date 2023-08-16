package com.projectdemo.manage.bean;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "customer_service")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class CustomerServiceBean {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name", columnDefinition = "nvarchar(12)")
    private String name;

    @Column(name = "account", columnDefinition = "varchar(20)")
    private String account;

    @Column(name = "password", columnDefinition = "varchar(20)")
    private String password;
    
    @Column(name = "authorizations", columnDefinition = "varchar(4)")
    private String authorizations;
        
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customerService")
	@JsonIgnore
    private List<ShopHistoryMessageBean> shopHistoryMessage;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(String authorizations) {
		this.authorizations = authorizations;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<ShopHistoryMessageBean> getShopHistoryMessage() {
		return shopHistoryMessage;
	}

	public void setShopHistoryMessage(List<ShopHistoryMessageBean> shopHistoryMessage) {
		this.shopHistoryMessage = shopHistoryMessage;
	}

    
    
}
