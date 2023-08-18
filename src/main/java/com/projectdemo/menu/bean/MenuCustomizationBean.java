package com.projectdemo.menu.bean;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "menu_customization")
public class MenuCustomizationBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", columnDefinition = "int")
	private Integer id;
	
	@ManyToOne
	@JoinColumn(name = "fk_dish_id")
	private DishBean dish;
	
	@Column(name = "customization_name" , columnDefinition = "nvarchar(50)")
	private String customizationName;
	
	@Column(name = "min_selection", columnDefinition = "int")
	private Integer minSelection;
	
	@Column(name = "max_selection", columnDefinition = "int")
	private Integer maxSelection;
	
	@Column(name = "repeatable" , columnDefinition = "bit")
	private boolean repeatable;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "menuCustomization")
	@JsonIgnore
	private List<MenuCustomizationOptionsBean> menuCustomizationOptions;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public DishBean getDish() {
		return dish;
	}

	public void setDish(DishBean dish) {
		this.dish = dish;
	}

	public String getCustomizationName() {
		return customizationName;
	}

	public void setCustomizationName(String customizationName) {
		this.customizationName = customizationName;
	}

	public Integer getMinSelection() {
		return minSelection;
	}

	public void setMinSelection(Integer minSelection) {
		this.minSelection = minSelection;
	}

	public Integer getMaxSelection() {
		return maxSelection;
	}

	public void setMaxSelection(Integer maxSelection) {
		this.maxSelection = maxSelection;
	}

	public boolean isRepeatable() {
		return repeatable;
	}

	public void setRepeatable(boolean repeatable) {
		this.repeatable = repeatable;
	}

	public List<MenuCustomizationOptionsBean> getMenuCustomizationOptions() {
		return menuCustomizationOptions;
	}

	public void setMenuCustomizationOptions(List<MenuCustomizationOptionsBean> menuCustomizationOptions) {
		this.menuCustomizationOptions = menuCustomizationOptions;
	}

	


}
