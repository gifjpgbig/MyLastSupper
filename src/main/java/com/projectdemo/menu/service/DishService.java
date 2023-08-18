package com.projectdemo.menu.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectdemo.menu.bean.DishBean;
import com.projectdemo.menu.dao.DishRepository;


@Service
public class DishService {
	@Autowired
	private DishRepository dishRepository;

	public DishBean creatDish(DishBean dishBean) {
		return dishRepository.save(dishBean);
	}

	public DishBean getDishById(Integer id) {
		return dishRepository.findById(id).orElse(null);
	}

	public List<DishBean> getDishAll() {
		return dishRepository.findAll();
	}

	public DishBean updateDish(DishBean dishBean) {
		if (dishRepository.existsById(dishBean.getId())) {
			return dishRepository.save(dishBean);
		}
		return null;
	}

	public boolean deleteDish(Integer id) {
		if (dishRepository.findById(id).isPresent()) {
			dishRepository.deleteById(id);
			return true;
		}
		return false;
	}
}
