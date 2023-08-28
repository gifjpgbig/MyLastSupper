package com.projectdemo.order.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.projectdemo.order.bean.DeliverDetailBean;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.order.service.DeliverDetailService;
import com.projectdemo.order.service.OrderListService;

import jakarta.transaction.Transactional;

@RestController
@CrossOrigin()
public class OrderListController {

	@Autowired
	private OrderListService olService;

	@Autowired
	private DeliverDetailService ddService;

	// 未分類
	// 用客戶id去搜尋，暫時沒用到
	@PostMapping("/order/findByCustomerId/{id}")
	public String findBycustomerId(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		List<OrderListBean> orders = olService.findOrderByCustomerId(id);
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				JSONObject item = new JSONObject().put("id", order.getId()).put("address", order.getAddress())
						.put("cus_status", order.getCusStatus()).put("deliver_status", order.getDeliverStatus())
						.put("shop_status", order.getShopStatus())
						.put("customerID", order.getCustomer().getCustomerID())
						.put("customer", order.getCustomer().toString());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 用店家id去搜尋，暫時沒用到
	@PostMapping("/order/findByShopId/{id}")
	public List<OrderListBean> findByShopId(@PathVariable Integer id) {
		return olService.findOrderByShopId(id);
	}

	// 寫在add.vue的測試新增訂單，目前有bug尚未解決
	// 操作方法是在vue project裡面寫一個model: order.js
	@PostMapping("/order/insert1")
	public String insert1(@RequestBody OrderListBean ol) {
		JSONObject responseJson = new JSONObject();
		OrderListBean order = olService.addOrder(ol);
		if (order != null) {
			responseJson.put("message", "新增成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "新增失敗");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

	// 在edit.vue載入的同時，拿到該筆資料
	@PostMapping("/order/findById/{id}")
	public OrderListBean findById(@PathVariable Integer id) {
		return olService.findOrderById(id);
	}

	// 目前沒用到
	@PutMapping("/order/update1/{id}")
	public void update1(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateOrderById(id, ol);
	}

	// 改變訂單狀態，可以被應用在許多地方
	// orders.vue
	// edit.vue
	@PutMapping("/order/update/status/{id}")
	public String updateStatus(@PathVariable Integer id, @RequestBody OrderListBean ol, @RequestParam String type) {
		JSONObject responseJson = new JSONObject();
		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			OrderListBean result = olService.updateStatusById(id, ol, type);
			if (result != null) {
				responseJson.put("message", "資料更新成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "資料更新失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

	// orders.vue，刪除訂單功能，應該不會使用
	@DeleteMapping("/order/delete1/{id}")
	public String delete1(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			boolean remove = olService.deleteODById(id);
			if (remove != false) {
				responseJson.put("message", "資料刪除成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "資料刪除失敗");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

// 店家
	// 改變店家回覆客戶的評論，目前還沒用到
	@PutMapping("/order/update/reply/{id}")
	public String updateReply(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		JSONObject responseJson = new JSONObject();
		OrderListBean olb = olService.findOrderById(id);
		// 偵測到客戶沒有評論
		if (olb.getDishComments() == null) {
			responseJson.put("message", "該筆訂單沒有評論，店家無法進行回覆");
			responseJson.put("success", false);
		} else {
			OrderListBean uolb = olService.updateReplyById(id, ol);
			if (uolb != null) {
				responseJson.put("message", "評論回覆成功!");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "評論回覆失敗，請聯繫客服人員!");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();
	}

// 客戶
	// 改變客戶針對訂單的評論、店家的評價、餐點的評論
	// OrderReviewToast.vue
	// dishComments: props2.dishComments,
	// shopComments: props2.shopComments,
	// shopReview: props2.shopReview,
	// 先判斷輸入資料是否完整，再根據狀態回覆對應的訊息，也做到對不雅字眼的偵測
	@PutMapping("/order/update/reviews/{id}")
	public String updateReviews(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		JSONObject responseJson = new JSONObject();

		if (ol.getDishComments().equals("") || ol.getShopComments().equals("") || ol.getShopReview() == null) {
			responseJson.put("message", "請輸入資料");
			responseJson.put("text", "不可以有空白的欄位唷");
			responseJson.put("success", false);
			return responseJson.toString();
		}

		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
			return responseJson.toString();
		}

		OrderListBean olb = olService.findOrderById(id);

		if ((olb.getShopComments() != null || olb.getDishComments() != null || olb.getShopReview() != null)) {
			responseJson.put("message", "已填寫過評論");
			responseJson.put("text", "本平台不允許過度批判");
			responseJson.put("success", false);
		} else {
			OrderListBean result = olService.updateReviewsById(id, ol);
			if (result != null && !ol.getDishComments().contains("幹") && !ol.getShopComments().contains("幹")) {
				responseJson.put("message", "評論回覆成功");
				responseJson.put("text", "謝謝您的熱情回覆");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "評論回覆失敗");
				responseJson.put("text", "本平台禁止不雅字眼");
				responseJson.put("success", false);
			}
		}
		return responseJson.toString();

	}

// 外送員
	// 1-1. 讓外送員查看現在可以接的訂單，應該要放在外送員系統
	// 現在可以接的訂單邏輯是: 客戶、餐廳訂單狀態不屬於已棄單、外送訂單狀態不屬於已接單
	// 還沒被其他外送員接走的訂單
	// 被其他外送員放棄的訂單
	@GetMapping("/order/Takables")
	public String findIsOrderTakable() {
		JSONObject responseJson = new JSONObject();
		List<OrderListBean> orders = olService.findIsOrderTakable();
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				JSONObject item = new JSONObject()
						.put("id", order.getId())
						.put("address", order.getAddress())
						.put("cus_status", order.getCusStatus())
						.put("deliver_status", order.getDeliverStatus())
						.put("shop_status", order.getShopStatus())
						.put("customerID", order.getCustomer().getCustomerID())
						.put("shopID", order.getShop().getId())
						.put("shopName", order.getShop().getName())
						.put("deliverFee", order.getDeliveryFee())
						.put("orderTime", order.getOrderTime())
						.put("shopAddress", order.getShop().getAddress());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 1-2. 讓外送員接單，應該要放在外送員系統
	// 接單的邏輯是:
	// 將訂單狀態改變成為已接單
	// 在該筆訂單的外送明細新增一筆屬於該外送員的資料
	// 這是個連續的操作，應該要讓兩個都通過，才能夠視為成功，否則就rollback
	@Transactional
	@PutMapping("/order/takeOrders")
	public String takeOrderByDeliver(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		// 將訂單狀態改變成為已接單
		// orderid、deliver_status
		OrderListBean olb = olService.updateStatusById(json);
		if (olb != null) {
			responseJson.put("message", "接單成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "接單失敗");
			responseJson.put("success", false);
			throw new RuntimeException("接單失敗"); // 拋出異常來觸發回滾
		}

		// 在該筆訂單的外送明細新增一筆屬於該外送員的資料
		// driver、address、deliverID、orderid
		DeliverDetailBean ddb = ddService.addDD(json);
		if (ddb != null) {
			responseJson.put("message", responseJson.getString("message") + "訂單已處理");
		} else {
			responseJson.put("success", false);
			throw new RuntimeException("新增外送明細失敗，已有外送員接單"); // 拋出異常來觸發回滾
		}
		return responseJson.toString();
	}

// 客服
	// 找到全部的訂單，給客服人員使用，緊急修正，應該要是找到全部進行中的訂單才對
	// 進行中的邏輯: 外送不是已完成、客戶以及店家沒有棄單
	// orders.vue
	// start: 0, rows: 0, name: "", cusID: "", shopID: "", sortOrder: "asc",
	// sortType: "id",
	@PostMapping("/order/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		System.out.println(json);
		JSONObject countJson = new JSONObject(json);
		long count = olService.count(countJson);
		responseJson.put("count", count);

		Page<OrderListBean> orders = olService.findAllInProgress(json);
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				Integer cusID = 0;
				Integer shopID = 0;
				if (order.getCustomer().getCustomerID() != null) {
					cusID = order.getCustomer().getCustomerID();
				}
				if (order.getShop().getId() != null) {
					shopID = order.getShop().getId();
				}
				boolean show = false;
				if (order.getDishComments() == null && order.getShopComments() == null
						&& order.getShopReview() == null) {
					show = !show;
				}
				JSONObject item = new JSONObject().put("id", order.getId()).put("address", order.getAddress())
						.put("cus_status", order.getCusStatus()).put("deliver_status", order.getDeliverStatus())
						.put("shop_status", order.getShopStatus()).put("customerID", cusID).put("shopID", shopID)
						.put("showReview", show);

				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 2-1取消訂單
	// 外送員接單的延伸功能，緊急狀況由客服人員註銷已接單的外送員詳細，應該要放在客服系統
	// DeliverDetailService.java, OrderListService.java
	// deliver_detail_id, reason, deliver_status, orderid
	// 註銷成功之後要再推送一次訂單
	@Transactional
	@PutMapping("/order/terminate")
	public String terminator(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		if (olService.updateStatusById(json) != null) {
			responseJson.put("message", "註銷成功，已推送訂單");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "註銷失敗，不存在此訂單");
			responseJson.put("success", false);
			throw new RuntimeException("請注意，註銷失敗！不存在此訂單"); // 拋出異常來觸發回滾
		}
		if (ddService.terminate(json) != null) {
			responseJson.put("message", "註銷成功，已推送訂單");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "註銷失敗");
			responseJson.put("success", false);
			throw new RuntimeException("訂單狀態改變失敗，不存在該筆外送員明細"); // 拋出異常來觸發回滾
		}
		return responseJson.toString();
	}

	// 4-1外送員接受的訂單
	// 讓外送員查看自己接到的訂單
	// 這個邏輯是，查看自己已接單的訂單，且有選擇六個欄位
	// 外送地址，運送時間、運費、下單時間、店家地址、店名
	// dd.address as cus_address, dd.deliver_time, ol.delivery_fee,
	// ol.order_time, s.address as shop_address, s.name as shop_name,
	// 請注意，資料型態是object
	@PostMapping("/order/findInProgressByDeliver/{id}")
	public String findInProgressByDeliver(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		List<Object[]> myOrder = ddService.findInProgressByDeliver(id);
		JSONArray array = new JSONArray();
		for (Object order : myOrder) {
			JSONObject item = new JSONObject();
			item.put("order", order);
			array.put(item);
		}
		responseJson.put("list", array);
		return responseJson.toString();
//	{
//	    "list": [
//	        {
//	            "order": [
//	                "萬華產業園區幸福路一段update成功!",
//	                null,
//	                null,
//	                null,
//	                "酷狗路熊空街我沒K",
//	                "酷狗餐廳"
//	            ]
//	        }
//	        ]
//	}
	}

	// 5-1外送員完成訂單
	// 讓外送員可以把訂單完成
	// 這個邏輯是，把訂單的外送狀態改成已完成
	@PutMapping("/order/complete/{id}")
	public String complete(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		JSONObject datas = new JSONObject();
		datas
		.put("orderid", id)
		.put("deliver_status", "已完成");

		if (olService.updateStatusById(datas.toString()) != null) {
			responseJson.put("message", "更正成功，已完成訂單");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "更正失敗，不存在此訂單");
			responseJson.put("success", false);
			throw new RuntimeException("請注意，註銷失敗！不存在此訂單"); // 拋出異常來觸發回滾
		}
		return responseJson.toString();
	}

}
