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
import org.springframework.web.bind.annotation.RestController;

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

	// 找到全部的訂單
	// orders.vue
	@PostMapping("/order/find")
	public String find(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		System.out.println(json);
		JSONObject countJson = new JSONObject(json);
		long count = olService.count(countJson);
		responseJson.put("count", count);

		Page<OrderListBean> orders = olService.findAll(json);
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
						.put("status", order.getStatus()).put("customerID", cusID).put("shopID", shopID)
						.put("showReview", show);

				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 用客戶id去搜尋
	@PostMapping("/order/findByCustomerId/{id}")
	public String findBycustomerId(@PathVariable Integer id) {
		JSONObject responseJson = new JSONObject();
		List<OrderListBean> orders = olService.findOrderByCustomerId(id);
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				JSONObject item = new JSONObject().put("id", order.getId()).put("address", order.getAddress())
						.put("status", order.getStatus()).put("customerID", order.getCustomer().getCustomerID())
						.put("customer", order.getCustomer().toString());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	@PostMapping("/order/findByShopId/{id}")
	public List<OrderListBean> findByShopId(@PathVariable Integer id) {
		return olService.findOrderByShopId(id);
	}

	// 給vue的馬老ajax controller
	// add.vue
	@PostMapping("/order/insert1")
	public String insert1(@RequestBody OrderListBean ol) {
		JSONObject responseJson = new JSONObject();
//		JSONObject obj = new JSONObject(json);
//		Integer id = obj.isNull("id") ? null : obj.getInt("id");

//		if(productAjaxService.exists(id)) {
//			responseJson.put("message", "資料已存在");
//			responseJson.put("success", false);
//		} else {
		OrderListBean order = olService.addOrder(ol);
		if (order != null) {
			responseJson.put("message", "新增成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "新增失敗");
			responseJson.put("success", false);
		}
//		}
		return responseJson.toString();

	}
//	@PostMapping("/order/insert1")
//	public OrderListBean insert1(@RequestBody OrderListBean ol) {
//		return olService.addOrder(ol);
//	}

	// edit.vue
	@PostMapping("/order/findById/{id}")
	public OrderListBean findById(@PathVariable Integer id) {
		return olService.findOrderById(id);
	}

	@PutMapping("/order/update1/{id}")
	public void update1(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateOrderById(id, ol);
	}

	// 改變訂單狀態
	// orders.vue
	// edit.vue
	@PutMapping("/order/update/status/{id}")
	public String updateStatus(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		System.out.println(ol.toString());
		System.out.println(ol.getStatus());

		JSONObject responseJson = new JSONObject();
		if (!olService.exists(id)) {
			responseJson.put("message", "資料不存在");
			responseJson.put("success", false);
		} else {
			OrderListBean result = olService.updateStatusById(id, ol);
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

	// 改變客戶針對訂單的評論、店家的評價、餐點的評論
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

	// 改變店家回覆客戶的評論
	@PutMapping("/order/update/reply/{id}")
	public void updateReply(@PathVariable Integer id, @RequestBody OrderListBean ol) {
		olService.updateReplyById(id, ol);
	}

	// orders.vue
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

	// 讓外送員查看現在可以接的訂單
	// 現在可以接的訂單邏輯是: 訂單狀態不屬於已接單、已取消
	// 還沒被其他外送員接走的訂單
	// 被其他外送員放棄的訂單
	@GetMapping("/order/Takables")
	public String findIsOrderTakable() {
		JSONObject responseJson = new JSONObject();
		List<OrderListBean> orders = olService.findIsOrderTakable();
		JSONArray array = new JSONArray();
		if (orders != null && !orders.isEmpty()) {
			for (OrderListBean order : orders) {
				JSONObject item = new JSONObject().put("id", order.getId()).put("address", order.getAddress())
						.put("status", order.getStatus()).put("customerID", order.getCustomer().getCustomerID())
						.put("shopID", order.getShop().getId());
//						.put("customer", order.getCustomer().toString());
				array = array.put(item);
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}

	// 讓外送員接單
	// 接單的邏輯是:
	// 將訂單狀態改變成為已接單
	// 在該筆訂單的外送明細新增一筆屬於該外送員的資料
	
	//這是個連續的操作，應該要讓兩個都通過，才能夠視為成功，否則就rollback
	@Transactional
	@PutMapping("/order/takeOrders")
	public String takeOrderByDeliver(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();

		// 將訂單狀態改變成為已接單
		OrderListBean olb = olService.updateStatusById(json);
		if (olb != null) {
			responseJson.put("message", "接單成功");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "接單失敗");
			responseJson.put("success", false);
		}

		// 在該筆訂單的外送明細新增一筆屬於該外送員的資料
		DeliverDetailBean ddb = ddService.addDD(json);
		if (ddb != null) {
			responseJson.put("message", responseJson.getString("message")+"訂單已處理");
		} else {
			responseJson.put("success", false);
		}

		return responseJson.toString();
	}

}
