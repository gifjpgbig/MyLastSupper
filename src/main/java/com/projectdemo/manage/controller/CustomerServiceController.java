package com.projectdemo.manage.controller;

import java.io.IOException;
import java.util.Base64;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.projectdemo.manage.bean.CustomerServiceBean;
import com.projectdemo.manage.service.CustomerServiceService;

@RestController
@CrossOrigin()
public class CustomerServiceController {

	@Autowired
	private CustomerServiceService csService;

	private final SimpMessagingTemplate messagingTemplate;

	@Autowired
	public CustomerServiceController(SimpMessagingTemplate messagingTemplate) {
		this.messagingTemplate = messagingTemplate;
	}

	// websocket傳送訊息到前端
	@PostMapping("/approve")
	public String approveApplication(@RequestBody String json) {
		// 执行审核逻辑，假设审核通过
		// boolean isApproved = true;

		// 发送审核结果到特定主题
		messagingTemplate.convertAndSend("/topic/audit", "helllllllllllllllo bitchhhhhhh websuckit");
		return "fuckkkkkkkkkkkkkkkkkkkkk";
	}

	//審核成功
	@PutMapping("/manage/cs/newman")
	public String newman(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject data = new JSONObject(json);

		CustomerServiceBean csb = csService.isExists(data.getString("uid"));
		if (csb != null) {
			CustomerServiceBean newCsb = csService.updateAuth(csb, "user");
			if (newCsb != null) {
				responseJson.put("message", "審核完畢");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "審核失敗");
				responseJson.put("success", false);
			}
		} else {
			responseJson.put("message", "沒有這個資料，滾");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}
	
	// 找到員工列表
	@GetMapping("/manage/cs/findEmp")
	public String findEmp() {
		JSONObject responseJson = new JSONObject();
		
		List<CustomerServiceBean> managers = csService.findManager();
		List<CustomerServiceBean> users = csService.findUser();
		List<CustomerServiceBean> guests = csService.findGuest();

		JSONArray marray = new JSONArray();
		for (CustomerServiceBean csb : managers) {
			JSONObject item = new JSONObject();
			item.put("name", csb.getName()).put("email", csb.getAccount()).put("photo", csb.getPhotoURL()).put("uid",
					csb.getUid()).put("id", csb.getId());
			marray.put(item);
		}
		JSONArray uarray = new JSONArray();
		for (CustomerServiceBean csb : users) {
			JSONObject item = new JSONObject();
			item.put("name", csb.getName()).put("email", csb.getAccount()).put("photo", csb.getPhotoURL()).put("uid",
					csb.getUid()).put("id", csb.getId());
			uarray.put(item);
		}
		JSONArray garray = new JSONArray();
		for (CustomerServiceBean csb : guests) {
			JSONObject item = new JSONObject();
			item.put("name", csb.getName()).put("email", csb.getAccount()).put("photo", csb.getPhotoURL()).put("uid",
					csb.getUid()).put("apply", csb.getApplyText()).put("id", csb.getId());
			garray.put(item);
		}

		responseJson.put("manager", marray).put("user", uarray).put("guest", garray);

		return responseJson.toString();
	}

	// 提交申請書
	@PutMapping("/manage/cs/sendApply")
	public String sendApply(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject data = new JSONObject(json);

		CustomerServiceBean csb = csService.isExists(data.getString("uid"));
		if (csb != null) {
			CustomerServiceBean newCsb = csService.updateApplyText(csb, data.getString("applyText"));
			if (newCsb != null) {
				responseJson.put("message", "申請成功");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "申請失敗");
				responseJson.put("success", false);
			}
		} else {
			responseJson.put("message", "沒有這個資料，滾");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

	// google登入之後的java後端驗證邏輯
	@PostMapping("/manage/cs/loginWithAuth")
	public String loginWithAuth(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONObject datas = new JSONObject(json);
		String uid = datas.getString("uid");
		CustomerServiceBean exists = csService.isExists(uid);

		// 判斷是否是全新訪客
		if (exists != null) {
			// 使用者還是待審核狀態
			if (exists.getAuthorizations().equals("guest")) {
				responseJson.put("userAuth", "guest");
			} else if (exists.getAuthorizations().equals("deny")) {
				responseJson.put("userAuth", "deny");
			} else {
				// 已有身分的使用者，判斷是否是第一次使用此系統
				if (exists.isFirstLogin()) {
					responseJson.put("userAuth", exists.getAuthorizations());
					responseJson.put("isFirst", true);
				} else {
					responseJson.put("userAuth", exists.getAuthorizations());
					responseJson.put("isFirst", false);
				}
			}
		} else {
			// 全新訪客登入的狀況，新增一筆資料，並且跳轉畫面至填寫申請單
			CustomerServiceBean csb = new CustomerServiceBean();
			csb.setName(datas.getString("displayName"));
			csb.setAccount(datas.getString("email"));
			csb.setPhotoURL(datas.getString("photoURL"));
			csb.setUid(datas.getString("uid"));
			csService.createNewBean(csb);
			responseJson.put("userAuth", "guestTo");
		}

		return responseJson.toString();
	}

	// test
	@PostMapping("/manage/cs/add")
	public String add(@RequestPart String json, @RequestPart("photoFile") MultipartFile photoFile) {
		JSONObject responseJson = new JSONObject();

		JSONObject data = new JSONObject(json);

		try {
			byte[] photo = photoFile.getBytes();
			CustomerServiceBean csb = new CustomerServiceBean();
			csb.setAccount(data.getString("account"));
			csb.setAuthorizations(data.getString("auth"));
			csb.setName(data.getString("name"));
			csb.setPassword(data.getString("password"));
			csb.setPhoto(photo);

			CustomerServiceBean addCDB = csService.addCDB(csb);
			if (addCDB != null) {
				responseJson.put("message", "管理員新增成功！");
				responseJson.put("success", true);
			} else {
				responseJson.put("message", "管理員新增失敗！");
				responseJson.put("success", false);
			}
		} catch (IOException e) {
			responseJson.put("message", "相片規格不符合");
			responseJson.put("success", false);
			e.printStackTrace();
		}

		return responseJson.toString();
	}

	// test
	@PostMapping("/manage/cs/addPhoto/{id}")
	public String addShopPhoto(@PathVariable("id") Integer id, @RequestBody MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		CustomerServiceBean csb = new CustomerServiceBean();
		try {
			byte[] photo = photoFile.getBytes();
			csb.setPhoto(photo);
			CustomerServiceBean update = csService.update(id, csb);
			if (update != null) {
				json.put("success", true);
				json.put("message", "照片更新成功");
			} else {
				json.put("success", false);
				json.put("message", "照片更新失敗");
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			json.put("success", false);
			e.printStackTrace();
		}
		return json.toString();
	}

	// test
	/**
	 * GET PHOTO BY CUSSERVICE ID
	 * 
	 * @param id cusService ID
	 * @return Base64 photo
	 */
	@GetMapping("/manage/cs/findPhoto/{id}")
	public String getPhoto(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		CustomerServiceBean csb = csService.findById(id);
		if (csb == null) {
			return null;
		}

		byte[] photo = csb.getPhoto();
		if (photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			json.put("success", true);
			json.put("photo", base64Image);
		} else {
			json.put("success", false);
		}

		return json.toString();
	}

	// test
	@PostMapping("/manage/cs/login")
	public String login(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		CustomerServiceBean cs = csService.checkLogin(json);
		if (cs != null) {
			responseJson.put("csID", cs.getId());
			responseJson.put("csName", cs.getName());
			responseJson.put("auth", cs.getAuthorizations());
			responseJson.put("message", "登入成功！");
			responseJson.put("success", true);
		} else {
			responseJson.put("message", "登入失敗！");
			responseJson.put("success", false);
		}
		return responseJson.toString();
	}

}
