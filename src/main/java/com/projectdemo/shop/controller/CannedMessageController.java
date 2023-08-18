package com.projectdemo.shop.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.service.CannedMessageService;

@RestController
@RequestMapping("/shop/can")
@CrossOrigin()
public class CannedMessageController {

	@Autowired
	private CannedMessageService cannedMessageService;

	@PostMapping("/add")
	public String addCannedMessage(@RequestBody CannedMessageBean bean) {
		JSONObject json = new JSONObject();
		CannedMessageBean msg = cannedMessageService.addCannedMessage(bean);
		if (msg != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}

	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		CannedMessageBean bean = cannedMessageService.findById(id);

		if (bean != null) {
			ShopBean fk_shop = bean.getShop();
			JSONObject item = new JSONObject().put("id", bean.getId()).put("messageCDate", bean.getMessageCDate())
					.put("messageUDate", bean.getMessageUDate()).put("moneyRange", bean.getMoneyRange())
					.put("scoreRange", bean.getScoreRange()).put("messageSendTime", bean.getMessageSendTime())
					.put("messageText", bean.getMessageText());
			JSONObject shop = new JSONObject().put("shopName", fk_shop.getName()).put("shopId", fk_shop.getId());
			array = array.put(item).put(shop);
		}
		json.put("cannedMessage", array);
		return json.toString();
	}

	@GetMapping("/all/{id}")
	public String findAllByShop(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<CannedMessageBean> list = cannedMessageService.findAllByShopId(id);
		if (list != null && !list.isEmpty()) {
			for (CannedMessageBean bean : list) {
				JSONObject item = new JSONObject().put("id", bean.getId()).put("messageCDate", bean.getMessageCDate())
						.put("messageUDate", bean.getMessageUDate()).put("moneyRange", bean.getMoneyRange())
						.put("scoreRange", bean.getScoreRange()).put("messageSendTime", bean.getMessageSendTime())
						.put("messageText", bean.getMessageText());
				array.put(item);
			}
			json.put("list", array);
		} else {
			json.put("message", "invalid id");
		}
		return json.toString();
	}

	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody CannedMessageBean msgBean) {
		JSONObject json = new JSONObject();
		CannedMessageBean update = cannedMessageService.update(id, msgBean);
		if (update != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}

	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		CannedMessageBean bean = cannedMessageService.findById(id);
		if (bean != null) {
			boolean delete = cannedMessageService.delete(id);
			json.put("success", delete);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	@GetMapping("/allOrdered/{id}")
	public String findAllByShopOrdered(@PathVariable("id") Integer id) {
		JSONObject json = new JSONObject();
		JSONArray array = new JSONArray();
		List<CannedMessageBean> list = cannedMessageService.findAllByShopOrdered(id);
		
		if (list != null && !list.isEmpty()) {
			for (CannedMessageBean bean : list) {
				JSONObject item = new JSONObject().put("id", bean.getId()).put("messageCDate", bean.getMessageCDate())
						.put("messageUDate", bean.getMessageUDate()).put("moneyRange", bean.getMoneyRange())
						.put("scoreRange", bean.getScoreRange()).put("messageSendTime", bean.getMessageSendTime())
						.put("messageText", bean.getMessageText());
				array.put(item);
			}
			json.put("list", array);
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "invalid id");
		}
		return json.toString();
	}

}
