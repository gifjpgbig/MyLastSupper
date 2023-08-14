package com.projectdemo.model.bean.shop;

import java.time.LocalDateTime;
import java.util.List;

import com.projectdemo.model.bean.customer.FavoritesBean;
import com.projectdemo.model.bean.customer.ShoppingCartBean;
import com.projectdemo.model.bean.manage.ShopHistoryMessageBean;
import com.projectdemo.model.bean.menu.MenuBean;
import com.projectdemo.model.bean.order.OrderListBean;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;

@Entity
@Table(name = "shop")
public class ShopBean {
	
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

	@Column(name = "email", columnDefinition = "varchar(20)")
	private String email;

	@Column(name = "phone", columnDefinition = "varchar(10)")
	private String phone;

	@Column(name = "photo", columnDefinition = "varbinary(max)")
	private byte[] photo;

	@Column(name = "district", columnDefinition = "nvarchar(50)")
	private String district;
	
	@Column(name = "address", columnDefinition = "nvarchar(50)")
	private String address;

	@Column(name = "latitude" , columnDefinition = "nvarchar(50)")
	private String latitude;

	@Column(name = "longitude", columnDefinition = "nvarchar(50)")
    private String longitude;


	@Column(name = "review", columnDefinition = "int")
    private Integer review;


	@Column(name = "bank", columnDefinition = "nvarchar(50)")
    private String bank;

	@Column(name = "open_status", columnDefinition = "bit")
    private boolean openStatus;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "cdate", columnDefinition = "datetime")
	private LocalDateTime cdate;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "udate", columnDefinition = "datetime")
	private LocalDateTime udate;
	
	//還有一堆關聯
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<PrepTimeBean> prepTime;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<ShoppingCartBean> shoppingCart;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<CannedMessageBean> cannedMessage;
	

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<ShopCategoryBean> shopCategory;


	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<HolidayBean> holiday;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<MenuBean> menu;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<OrderListBean> orderList;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<ShopHistoryMessageBean> shopHistoryMessage;	
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "shop")
	private List<FavoritesBean> favorites;

	@OneToOne(cascade = CascadeType.ALL, mappedBy = "shop")
    private OpenHrBean openhrBean;
	
	
	
	
}
