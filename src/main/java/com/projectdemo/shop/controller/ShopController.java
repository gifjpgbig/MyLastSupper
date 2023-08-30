package com.projectdemo.shop.controller;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.opencsv.CSVReader;
import com.projectdemo.customer.bean.FavoritesBean;
import com.projectdemo.customer.bean.ShoppingCartBean;
import com.projectdemo.manage.bean.ShopHistoryMessageBean;
import com.projectdemo.menu.bean.MenuBean;
import com.projectdemo.order.bean.OrderListBean;
import com.projectdemo.shop.bean.CannedMessageBean;
import com.projectdemo.shop.bean.HolidayBean;
import com.projectdemo.shop.bean.OpenHrBean;
import com.projectdemo.shop.bean.PrepTimeBean;
import com.projectdemo.shop.bean.ShopBean;
import com.projectdemo.shop.bean.ShopCategoryBean;
import com.projectdemo.shop.bean.ShopDTO;
import com.projectdemo.shop.service.ShopService;

@RestController
@RequestMapping("/shop")
@CrossOrigin()
public class ShopController {

	@Autowired
	private ShopService shopService;
	
	@Autowired
	private ResourceLoader resourceLoader;

	/**
	 * CREATE (NO PHOTO)
	 * 
	 * @param shopBean ShopBean
	 * @return json success
	 */
	@PostMapping("/add")
	public String addShop(@RequestBody ShopBean shopBean) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.addShop(shopBean);
		if (shop != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
		}
		return json.toString();
	}
	
	/**
	 * CREATE (WITH PHOTO)
	 * uses RequestPart
	 * 
	 * @param jsonPayload ShopBean
	 * @param photoFile image file (jpg, png, etc)
	 * @return json success
	 */
	@PostMapping("/addWithPhoto")
	public String addShopWithPhoto(@RequestPart String jsonPayload, @RequestPart("photoFile") MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		JSONObject payload = new JSONObject(jsonPayload);
		try {
			byte[] photo = photoFile.getBytes();
			
			ShopBean shopBean = new ShopBean();
			shopBean.setName(payload.getString("name"));
			shopBean.setAccount(payload.getString("account"));
			shopBean.setPassword(payload.getString("password"));
			shopBean.setEmail(payload.getString("email"));
			shopBean.setPhone(payload.getString("phone"));
			shopBean.setDistrict(payload.getString("district"));
			shopBean.setAddress(payload.getString("address"));
			shopBean.setBank(payload.getString("bank"));
			shopBean.setLatitude(payload.getString("latitude"));
			shopBean.setLongitude(payload.getString("longitude"));
			
			shopBean.setPhoto(photo);
			ShopBean shop = shopService.addShop(shopBean);
			if (shop != null) {
				json.put("success", true);
				json.put("shopId", shop.getId());
			} else {
				json.put("success", false);
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * ADD PHOTO TO EXISTING SHOP
	 * 
	 * @param id Shop ID
	 * @param photoFile image file
	 * @return json success
	 */
	@PostMapping("/addPhoto/{id}")
	public String addShopPhoto(@PathVariable("id") Integer id, @RequestBody MultipartFile photoFile) {
		JSONObject json = new JSONObject();
		ShopBean shopBean = shopService.findById(id);
		try {
			byte[] photo = photoFile.getBytes();
			shopBean.setPhoto(photo);
			ShopBean update = shopService.update(id, shopBean);
			if (update != null) {
				json.put("success", true);
			} else {
				json.put("success", false);
			}
		} catch (IOException e) {
			json.put("message", "invalid photo");
			e.printStackTrace();
		}
		return json.toString();
	}
	
	/**
	 * GET PHOTO BY SHOP ID
	 * 
	 * @param id Shop ID
	 * @return Base64 photo
	 */
	@GetMapping("/findPhoto/{id}") //shop id
	public String getPhoto(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		
		if(shop == null) {
			return null;
		}
		
		byte[] photo = shop.getPhoto();
		if(photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			json.put("success", true);
			json.put("photo", base64Image);
		}
		else {
			json.put("success", false);
		}
		return json.toString();
	}
	
	/**
	 * FIND ALL SHOPS
	 * 
	 * @return ShopDTO array
	 */
	@GetMapping("/all")
	public String findAll() {
		JSONObject json = new JSONObject();
		List<ShopBean> list = shopService.findAll();
		JSONArray array = new JSONArray();
		for (ShopBean bean : list) {
			ShopDTO shopDTO = convertToShopDTO(bean);
			JSONObject object = new JSONObject(shopDTO);
			array.put(object);
		}
		json.put("list", array);
		return json.toString();
	}

	
	/**
	 * FIND SHOP BY ID
	 * 
	 * @param id Shop ID
	 * @return ShopDTO, json success
	 */
	@GetMapping("/{id}")
	public String findById(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			// Use DTO to create suitable JSON object
			ShopDTO shopDTO = convertToShopDTO(shop);
//			json.put("shop", new JSONObject(shop));
			json.put("shop", new JSONObject(shopDTO));
			json.put("success", true);
		}
		else {
			json.put("success", false);
			json.put("message", "invalid id");
		}
		return json.toString();
	}
	
	/**
	 * Convert ShopBean to ShopDTO
	 * 
	 * @param bean ShopBean
	 * @return ShopDTO
	 */
	private ShopDTO convertToShopDTO(ShopBean bean) {
		ShopDTO dto = new ShopDTO();
		
		// Shop columns
		dto.setId(bean.getId());
		dto.setName(bean.getName());
		dto.setAccount(bean.getAccount());
		dto.setPassword(bean.getPassword());
		dto.setEmail(bean.getEmail());
		dto.setPhone(bean.getPhone());
		dto.setDistrict(bean.getDistrict());
		dto.setAddress(bean.getAddress());
		dto.setLatitude(bean.getLatitude());
		dto.setLongitude(bean.getLongitude());
		dto.setReview(bean.getReview());
		dto.setBank(bean.getBank());
		dto.setOpenStatus(bean.isOpenStatus());
		dto.setCdate(bean.getCdate());
		dto.setUdate(bean.getUdate());
		
		// Open Hour
		if(bean.getOpenhrBean() != null) {
			dto.setOpenhrId(bean.getOpenhrBean().getId());
		}
		
		// Photo
		byte[] photo = bean.getPhoto();
		if(photo != null) {
			String base64Image = Base64.getEncoder().encodeToString(photo);
			dto.setPhoto(base64Image);
		}
		
		// Prep Time
		List<PrepTimeBean> prepTime = bean.getPrepTime();
		ArrayList<Integer> prepTimeList = new ArrayList<Integer>();
		for(PrepTimeBean prep : prepTime) {
			prepTimeList.add(prep.getId());
		}
		dto.setPrepTimeId(prepTimeList);
		
		// Shopping Cart
		List<ShoppingCartBean> shoppingCart = bean.getShoppingCart();
		ArrayList<Integer> shoppingCartList = new ArrayList<Integer>();
		for(ShoppingCartBean cart : shoppingCart) {
			shoppingCartList.add(cart.getId());
		}
		dto.setShoppingCartId(shoppingCartList);
		
		// Canned Message
		List<CannedMessageBean> cannedMessage = bean.getCannedMessage();
		ArrayList<Integer> cannedMessageList = new ArrayList<Integer>();
		for(CannedMessageBean msg : cannedMessage) {
			cannedMessageList.add(msg.getId());
		}
		dto.setCannedMessageId(cannedMessageList);
		
		// Shop Category
		List<ShopCategoryBean> shopCategory = bean.getShopCategory();
		ArrayList<Integer> shopCategoryList = new ArrayList<Integer>();
		for(ShopCategoryBean cat : shopCategory) {
			shopCategoryList.add(cat.getId());
		}
		dto.setShopCategoryId(shopCategoryList);
		
		// Holiday
		List<HolidayBean> holiday = bean.getHoliday();
		ArrayList<Integer> holidayList = new ArrayList<Integer>();
		for(HolidayBean hol : holiday) {
			holidayList.add(hol.getId());
		}
		dto.setHolidayId(holidayList);
		
		// Menu
		List<MenuBean> menu = bean.getMenu();
		ArrayList<Integer> menuList = new ArrayList<Integer>();
		for(MenuBean men : menu) {
			menuList.add(men.getId());
		}
		dto.setMenuId(menuList);
		
		// Order List
		List<OrderListBean> orderList = bean.getOrderList();
		ArrayList<Integer> orderArrayList = new ArrayList<Integer>();
		for(OrderListBean list: orderList) {
			orderArrayList.add(list.getId());
		}
		dto.setOrderListId(orderArrayList);
		
		// Shop History Message
		List<ShopHistoryMessageBean> shopHistoryMessage = bean.getShopHistoryMessage();
		ArrayList<Integer> shopHistoryList = new ArrayList<Integer>();
		for(ShopHistoryMessageBean hist : shopHistoryMessage) {
			shopHistoryList.add(hist.getId());
		}
		dto.setShopHistoryMessageId(shopHistoryList);
		
		// Favorites
		List<FavoritesBean> favorites = bean.getFavorites();
		ArrayList<Integer> favoritesList = new ArrayList<Integer>();
		for(FavoritesBean fav : favorites) {
			favoritesList.add(fav.getId());
		}
		dto.setFavoritesId(favoritesList);
		
		return dto;
	}

	/**
	 * UPDATE
	 * 
	 * @param id Shop ID
	 * @param shopBean
	 * @return json success
	 */
	@PutMapping("/{id}")
	public String update(@PathVariable Integer id, @RequestBody ShopBean shopBean) {
		JSONObject json = new JSONObject();
		ShopBean update = shopService.update(id, shopBean);
		if (update != null) {
			json.put("success", true);
		} else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	/**
	 * DELETE
	 * 
	 * @param id Shop ID
	 * @return json success
	 */
	@DeleteMapping("/{id}")
	public String delete(@PathVariable Integer id) {
		JSONObject json = new JSONObject();
		ShopBean shop = shopService.findById(id);
		if(shop != null) {
			boolean delete = shopService.delete(id);
			json.put("success", delete);
		}
		else {
			json.put("success", false);
			json.put("message", "id doesn't exist");
		}
		return json.toString();
	}
	
	/**
	 * FIND SHOP BY NAME
	 * 
	 * @param name String
	 * @return Shop
	 */
	@GetMapping("/name")
	public String findByName(@RequestParam("name") String name) {
		JSONObject json = new JSONObject();
		if(name != null) {
			ShopBean shopBean = shopService.findByName(name);
			if(shopBean != null) {
				ShopDTO shopDTO = convertToShopDTO(shopBean);
				json.put("shop", new JSONObject(shopDTO));
			}
			else {
				json.put("message", "No results found");
			}
		}
		else {
			json.put("message", "No results found");
		}
		
		return json.toString();
	}
	
	/**
	 * Shop Fuzzy Search
	 * 
	 * @param name String
	 * @return Shop
	 */
	@GetMapping("/nameFuzzy")
	public String findByNameFuzzy(@RequestParam("name") String name) {
		JSONObject json = new JSONObject();
		if(name != null) {
			JSONArray array = new JSONArray();
			List<ShopBean> list = shopService.findFuzzy(name);
			if(! list.isEmpty()) {
				for (ShopBean bean : list) {
					ShopDTO shopDTO = convertToShopDTO(bean);
					JSONObject object = new JSONObject(shopDTO);
					array.put(object);
				}
				json.put("list", array);
			}
			else {
				json.put("message", "No results found");
			}
		}
		else {
			json.put("message", "No results found");
		}
		
		return json.toString();
	}
	
	/**
	 * Login process
	 * 
	 * @param loginData Map containing account & password
	 * @return Shop ID if login success
	 */
	@PostMapping("/login")
	public String login(@RequestBody Map<String, String> loginData) {
		JSONObject json = new JSONObject();
		String username = loginData.get("account");
		String password = loginData.get("password");
		
		int loginValidate = shopService.loginValidate(username, password);
		if(loginValidate != -1) {
			json.put("shopId", loginValidate);
			json.put("validate", true);
		}
		else {
			json.put("validate", false);
		}
		return json.toString();
	}
	
	@GetMapping("/pagination")
	public String findAllPagination(@RequestBody String json) {
		JSONObject responseJson = new JSONObject();
		JSONArray array = new JSONArray();
		
		Page<ShopBean> shops = shopService.findAllPage(json);
		if(shops != null && ! shops.isEmpty()) {
			for(ShopBean shop : shops) {
				ShopDTO shopDTO = convertToShopDTO(shop);
				array = array.put(new JSONObject(shopDTO));
			}
		}
		responseJson.put("list", array);
		return responseJson.toString();
	}
	
	/**
	 * Reads CSV file, uploads data to database
	 * Currently includes, Shop, ShopCategory, OpenHr
	 * Future update: Shop photo
	 * 
	 * NOTE: add OpenCSV dependency to pom.xml from Maven Repository, latest version (5.8)
	 * NOTE: requires up to date ShopService
	 * 
	 * @return json success
	 */
	@GetMapping("/uploadCSV")
	public String uploadShops() {
		JSONObject json = new JSONObject();
		
		// Get csv file, read into List
		Path filePath = Paths.get("C:/temp/dbshop.csv");
		List<String[]> list = readAllLines(filePath);
		
		// Get image folder, read into List
		Path folderPath = Paths.get("C:/temp/dbimages");
		File folder = folderPath.toFile();
		//json.put("folder", folder.isDirectory());
		List<byte[]> images = new ArrayList<byte[]>();
		
		
		File[] files = folder.listFiles();
		// File names are default sorted lexicographically, sort them numerically
		Arrays.sort(files, Comparator.comparingInt(file -> Integer.parseInt(file.getName().split("\\.")[0])));
		for(File file : files) {
			String name = file.getName();
			Resource resource = resourceLoader.getResource("file:" + folderPath + "/" + name);
			try {
				byte[] image = Files.readAllBytes(resource.getFile().toPath());
				images.add(image);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		// Get shop info and batch insert, return created shops
		List<ShopBean> shops = saveAll(list, images);
		if(shops != null) {
			json.put("add shops success", true);
		}
		
		// Get list of IDs of created shops
		List<Integer> ids = new ArrayList<Integer>();
		for(ShopBean shop : shops) {
			ids.add(shop.getId());
		}
		
		// Add categories
		List<ShopCategoryBean> addCategories = addCategories(list, ids);
		if(addCategories != null) {
			json.put("add categories success", true);
		}
		
		// Add open hours
		List<OpenHrBean> addOpenHrs = addOpenHrs(list, ids);
		if(addOpenHrs != null) {
			json.put("add openHrs success", true);
		}
		
		//json.put("fileName", folderPath.getFileName());
		//json.put("parent", folderPath.getParent());
		//json.put("root", folderPath.getRoot());
		json.put("finish", "finished");
		return json.toString();
	}
	
	/**
	 * CSV Reader
	 * 
	 * @param filePath
	 * @return List<String[]>
	 */
	public List<String[]> readAllLines(Path filePath) {
		try(Reader reader = Files.newBufferedReader(filePath);
			CSVReader csvReader = new CSVReader(reader);) {
			return csvReader.readAll();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * Insert OpenHrs to database from CSV
	 * Open Hrs: split by |
	 * shows time in format MM:SS-MM:SS for open-close
	 * ALL: every day, else NO
	 * if NO, time is followed by days it applies to, split by /
	 * Examples:
	 * 		ALL|0800-20:00
	 * 		NO|06:30-12:00/12345|06:30-13:00/67
	 * 
	 * @param catList rows from csv file
	 * @param idList Shop ID list
	 * @return
	 */
	public List<OpenHrBean> addOpenHrs(List<String[]> catList, List<Integer> idList) {
		List<OpenHrBean> openHrs = new ArrayList<OpenHrBean>();
		Iterator<String[]> iterator = catList.iterator();
		Iterator<Integer> idIterator = idList.iterator();
		
		iterator.next(); // skip column names
		while(iterator.hasNext()) {
			String[] next = iterator.next();
			Integer id = idIterator.next();
			ShopBean shop = shopService.findById(id);
			
			String[] splits = next[6].split("\\|");
			
			// Determine if every day or not
			if(splits[0].equals("ALL")) {
				// Format example: [ALL, 06:30-15:00]
				int startMin = Integer.parseInt(splits[1].substring(0, 2));
				int startSec = Integer.parseInt(splits[1].substring(3, 5));
				int endMin = Integer.parseInt(splits[1].substring(6, 8));
				int endSec = Integer.parseInt(splits[1].substring(9));
				
				LocalTime start = LocalTime.of(startMin, startSec);
				LocalTime end = LocalTime.of(endMin, endSec);
				
				OpenHrBean openHrBean = new OpenHrBean();
				openHrBean.setMonOpen(start);
				openHrBean.setMonClose(end);
				openHrBean.setTueOpen(start);
				openHrBean.setTueClose(end);
				openHrBean.setWedOpen(start);
				openHrBean.setWedClose(end);
				openHrBean.setThrOpen(start);
				openHrBean.setThrClose(end);
				openHrBean.setFriOpen(start);
				openHrBean.setFriClose(end);
				openHrBean.setSatOpen(start);
				openHrBean.setSatClose(end);
				openHrBean.setSunOpen(start);
				openHrBean.setSunClose(end);
				openHrBean.setShop(shop);
				
				openHrs.add(openHrBean);
			}
			else {
				// Format: [NO, 07:00-14:00/67, 07:15-16:00/12345]
				String[] range = Arrays.copyOfRange(splits, 1, splits.length); // remove first element
				OpenHrBean openHrBean = new OpenHrBean();
				
				for(String str : range) {
					LocalTime start = LocalTime.of(Integer.parseInt(str.substring(0, 2)), Integer.parseInt(str.substring(3, 5)));
					LocalTime end = LocalTime.of(Integer.parseInt(str.substring(6, 8)), Integer.parseInt(str.substring(9, 11)));
					
					// Get the days part of string
					String[] days = str.split("/")[1].split("");
					for(String day : days) {
						switch(day) {
						case "1": 
							openHrBean.setMonOpen(start);
							openHrBean.setMonClose(end);
							break;
						case "2":
							openHrBean.setTueOpen(start);
							openHrBean.setTueClose(end);
							break;
						case "3":
							openHrBean.setWedOpen(start);
							openHrBean.setWedClose(end);
							break;
						case "4":
							openHrBean.setThrOpen(start);
							openHrBean.setThrClose(end);
							break;
						case "5":
							openHrBean.setFriOpen(start);
							openHrBean.setFriClose(end);
							break;
						case "6":
							openHrBean.setSatOpen(start);
							openHrBean.setSatClose(end);
							break;
						case "7":
							openHrBean.setSunOpen(start);
							openHrBean.setSunClose(end);
							break;
						}
					}
				}
				openHrBean.setShop(shop);
				openHrs.add(openHrBean);
			}
		}
		return shopService.openHrBatchInsert(openHrs);
	}
	
	/**
	 * Insert ShopCategory to database from CSV
	 * ShopCategory: split by |
	 * Example:
	 * 		Coffee|Tea|Dessert
	 * 
	 * @param catList rows from csv file
	 * @param idList Shop ID list
	 * @return
	 */
	public List<ShopCategoryBean> addCategories(List<String[]> catList, List<Integer> idList) {
		List<ShopCategoryBean> cats = new ArrayList<ShopCategoryBean>();
		Iterator<String[]> iterator = catList.iterator();
		Iterator<Integer> idIterator = idList.iterator();
		
		iterator.next(); // skip column names
		while(iterator.hasNext()) {
			String[] next = iterator.next();
			Integer id = idIterator.next();
			ShopBean shop = shopService.findById(id);
			
			// Get all categories for this shop, make each a bean, add to list
			String[] categories = next[5].split("\\|");
			for(String cat : categories) {
				ShopCategoryBean shopCategoryBean = new ShopCategoryBean();
				shopCategoryBean.setName(cat);
				shopCategoryBean.setShop(shop);
				cats.add(shopCategoryBean);
			}
		}
		return shopService.catBatchInsert(cats);
	}
	
	/**
	 * Insert Shops to database from CSV
	 * Includes name, address, latitude, longitude
	 * 
	 * @param list rows from csv file
	 * @return
	 */
	public List<ShopBean> saveAll(List<String[]> list, List<byte[]> images) {
		List<ShopBean> shops = new ArrayList<ShopBean>();
		Iterator<String[]> iteratorList = list.iterator();
		Iterator<byte[]> iteratorImg = images.iterator();
		Random random = new Random();
		
		iteratorList.next(); // skip column names
		
		int count = 1;
		while(iteratorList.hasNext()) {
			String[] next = iteratorList.next();
			
			ShopBean shopBean = new ShopBean();
			shopBean.setName(next[1]);
			shopBean.setAddress(next[2]);
			shopBean.setLatitude(next[3]);
			shopBean.setLongitude(next[4]);
			shopBean.setReview(random.nextInt(5) + 1);
			shopBean.setPhoto(iteratorImg.next());
			
			shopBean.setAccount("test" + Integer.toString(count));
			count++;
			shopBean.setPassword("1234");
			
			shops.add(shopBean);
		}
		return shopService.batchInsert(shops);
	}
}
