package com.projectdemo.menu.bean;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_customization_options")
public class MenuCustomizationOptionsBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_menu_customization_options_id")
	private MenuCustomizationBean menuCustomization;
	
	@Column(name = "option_name" , columnDefinition = "nvarchar(50)")
	private String optionName;
	
	@Column(name = "option_money" , columnDefinition = "nvarchar(50)")
	private String optionMoney;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public MenuCustomizationBean getMenuCustomization() {
		return menuCustomization;
	}

	public void setMenuCustomization(MenuCustomizationBean menuCustomization) {
		this.menuCustomization = menuCustomization;
	}

	public String getOptionName() {
		return optionName;
	}

	public void setOptionName(String optionName) {
		this.optionName = optionName;
	}

	public String getOptionMoney() {
		return optionMoney;
	}

	public void setOptionMoney(String optionMoney) {
		this.optionMoney = optionMoney;
	}

	
	
}
