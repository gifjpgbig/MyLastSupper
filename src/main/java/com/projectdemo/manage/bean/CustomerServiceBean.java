package com.projectdemo.manage.bean;

import java.time.LocalDateTime;
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
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "customer_service")
@JsonIgnoreProperties(value = { "hibernateLazyInitializer", "handler" })
public class CustomerServiceBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;

	@Column(name = "name", columnDefinition = "nvarchar(12)")
	private String name;

	@Column(name = "account", columnDefinition = "varchar(50)")
	private String account;

	@Column(name = "password", columnDefinition = "varchar(20)")
	private String password;

	@Column(name = "authorizations", columnDefinition = "varchar(10)")
	private String authorizations;

	@Column(name = "photo", columnDefinition = "varbinary(max)")
	private byte[] photo;

	@Column(name = "photo_url", columnDefinition = "varchar(max)")
	private String photoURL;

	@Column(name = "uid", columnDefinition = "varchar(max)")
	private String uid;

	@Column(name = "apply_text", columnDefinition = "nvarchar(max)")
	private String applyText;

	@Column(name = "reply_text", columnDefinition = "nvarchar(max)")
	private String replyText;

	@Column(name = "is_first_login", columnDefinition = "bit")
	private boolean isFirstLogin;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customerService")
	@JsonIgnore
	private List<ShopHistoryMessageBean> shopHistoryMessage;

	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		}
	}

	public String getReplyText() {
		return replyText;
	}

	public void setReplyText(String replyText) {
		this.replyText = replyText;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAuthorizations() {
		return authorizations;
	}

	public void setAuthorizations(String authorizations) {
		this.authorizations = authorizations;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getPhotoURL() {
		return photoURL;
	}

	public void setPhotoURL(String photoURL) {
		this.photoURL = photoURL;
	}

	public String getUid() {
		return uid;
	}

	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getApplyText() {
		return applyText;
	}

	public void setApplyText(String applyText) {
		this.applyText = applyText;
	}

	public boolean isFirstLogin() {
		return isFirstLogin;
	}

	public void setFirstLogin(boolean isFirstLogin) {
		this.isFirstLogin = isFirstLogin;
	}

	public List<ShopHistoryMessageBean> getShopHistoryMessage() {
		return shopHistoryMessage;
	}

	public void setShopHistoryMessage(List<ShopHistoryMessageBean> shopHistoryMessage) {
		this.shopHistoryMessage = shopHistoryMessage;
	}

}
