package com.projectdemo.deliver.bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.projectdemo.manage.bean.DelivererHistoryMessageBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "deliverer")
@JsonIgnoreProperties(value = {"hibernateLazyInitializer", "handler"})
public class DelivererBean {

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

	@Column(name = "email", columnDefinition = "varchar(30)")
	private String email;

	@Column(name = "phone", columnDefinition = "varchar(10)")
	private String phone;

	@Column(name = "birthday", columnDefinition = "date" )
	private LocalDate birthday;

	@Column(name = "photo", columnDefinition = "varbinary(max)" )
	private byte[] photo;
	
	//總接單次數
	@Column(name="totalordercount")
	private Integer totalOrderCount;
	
	//總共棄單次數
	@Column(name="totaldeclinecount")
	private Integer totalDeclineCount;
	
	//棄單日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "declineDate", columnDefinition = "datetime")
	private LocalDateTime declineDate;
	
	//連續棄單次數
	@Column(name="continuedeclinecount")
	private Integer continueDeclineCount;
	
	// 創建日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "createDate", columnDefinition = "datetime")
	private LocalDateTime createDate;

	// 最新更新日期
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "updateDate", columnDefinition = "datetime")
	private LocalDateTime updateDate;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliverer")
	@JsonIgnore
	private List<TransportationBean> transportations;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "deliverer")
	@JsonIgnore
	private List<DelivererHistoryMessageBean> delivererHistoryMessage;
	
	@PrePersist
	public void onCreate() {
		if (createDate == null) {
			createDate = LocalDateTime.now();
		}
	}
	
	@PreUpdate
	public void onUpdate() {
		updateDate = LocalDateTime.now();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public LocalDateTime getCreateDate() {
		return createDate;
	}

	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}

	public LocalDateTime getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(LocalDateTime updateDate) {
		this.updateDate = updateDate;
	}

	public List<TransportationBean> getTransportations() {
		return transportations;
	}

	public void setTransportations(List<TransportationBean> transportations) {
		this.transportations = transportations;
	}

	public Integer getTotalOrderCount() {
		return totalOrderCount;
	}

	public void setTotalOrderCount(Integer totalOrderCount) {
		this.totalOrderCount = totalOrderCount;
	}

	public Integer getTotalDeclineCount() {
		return totalDeclineCount;
	}

	public void setTotalDeclineCount(Integer totalDeclineCount) {
		this.totalDeclineCount = totalDeclineCount;
	}

	public LocalDateTime getDeclineDate() {
		return declineDate;
	}

	public void setDeclineDate(LocalDateTime declineDate) {
		this.declineDate = declineDate;
	}

	public Integer getContinueDeclineCount() {
		return continueDeclineCount;
	}

	public void setContinueDeclineCount(Integer continueDeclineCount) {
		this.continueDeclineCount = continueDeclineCount;
	}

	public List<DelivererHistoryMessageBean> getDelivererHistoryMessage() {
		return delivererHistoryMessage;
	}

	public void setDelivererHistoryMessage(List<DelivererHistoryMessageBean> delivererHistoryMessage) {
		this.delivererHistoryMessage = delivererHistoryMessage;
	}
	
	
	
}
