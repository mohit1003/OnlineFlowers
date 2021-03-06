package com.online.flowers;

import static org.mockito.Mockito.mock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;

import com.online.flowers.controller.CustomerController;
import com.online.flowers.dto.Customer;
import com.online.flowers.dto.MaxMinSoldFlower;
import com.online.flowers.dto.ReportingForChart;
import com.online.flowers.dto.Sales;
import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.model.ShopModel;
import com.online.flowers.util.AuthRequest;

public class TestData {

	private CustomerController _controller;

	static Customer customer;

	static CustomerModel customerModel;

	static Map<String, String> image;

	static MockMultipartFile file;

	static MaxMinSoldFlower flower;

	static List<ReportingForChart> charts;

	static ReportingForChart flowerReport;

	static ReportingForChart flowerReport1;

	static FlowersModel flowerModel;

	static AuthRequest authRequest;
	
	static ShopModel shopModel;
	
	static List<ShopModel> shopList;
	
	static Sales sales;
	

	public static void setUp() {
		customer = new Customer("1@2", "MR", "Mohit", "Kulkarni", "123, High street, Brentford, London", "London", "UK",
				"$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeu0S", "9921918100");

		customerModel = new CustomerModel("1@2", "MR", "Mohit", "Kulkarni", "123, High street, Brentford, London",
				"London", "UK", "$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeu0S", "9921918100");
		image = new HashMap<>();
		image.put("signature", "69465fe2acaeb4d6a752e25821b668bc586b581c");
		image.put("format", "jpg");
		image.put("resource_type", "image");
		image.put("secure_url",
				"https://res.cloudinary.com/dxpstpk9n/image/upload/v1623641592/ubvxivmt6ukbtzgn4mlm.jpg");
		image.put("created_at", "2021-06-14T03:33:12Z");
		image.put("asset_id", "e4d788ab6c7f87368019c2b34f4e5c9e");
		image.put("version_id", "3e7a2efb26a09c596e37b04f6c71eab7");
		image.put("type", "upload");
		image.put("version", "1623641592");
		image.put("url", "http://res.cloudinary.com/dxpstpk9n/image/upload/v1623641592/ubvxivmt6ukbtzgn4mlm.jpg");
		image.put("tags", "[]");
		image.put("original_filename", "SympathyBoque");
		image.put("api_key", "178195379295372");
		image.put("bytes", "10147");
		image.put("width", "160");
		image.put("etag", "08458b81d56cbc453de5653e2b3b4416");
		image.put("placeholder", "false");
		image.put("original_extension", "jpeg");
		image.put("height", "315");

		file = new MockMultipartFile("file", "hello.txt", MediaType.TEXT_PLAIN_VALUE, "Hello, World!".getBytes());

		flowerModel = mock(FlowersModel.class);
		flower.setId(223);
		flower.setCategory("love");
		flower.setDescription("for you");
		flower.setName("Teddy");
		flower.setImageUrl("http://res.cloudinary.com/dxpstpk9n/image/upload/v1623596524/sprlfhvwojmxcaxaus7r.jpg");
		flower.setPrice("500");

		flower = mock(MaxMinSoldFlower.class);
		flower.setId(223);
		flower.setCategory("love");
		flower.setDescription("for you");
		flower.setName("Teddy");
		flower.setImageUrl("http://res.cloudinary.com/dxpstpk9n/image/upload/v1623596524/sprlfhvwojmxcaxaus7r.jpg");
		flower.setPrice("500");
		flower.setQuantity(5);

		charts = new ArrayList<>();
		flowerReport = mock(ReportingForChart.class);
		flower.setId(223);
		flower.setName("Teddy");
		flower.setPrice("500");
		flower.setQuantity(3);

		flowerReport1 = mock(ReportingForChart.class);
		flower.setId(233);
		flower.setName("Whity");
		flower.setPrice("200");
		flower.setQuantity(8);

		charts.add(flowerReport);
		charts.add(flowerReport1);

		authRequest = mock(AuthRequest.class);
		authRequest.setEmail("monit@gmail.com");
		authRequest.setPassword("sdfscsfedc sdcsd");
		
		shopModel = mock(ShopModel.class);
		shopModel.setImageId("22");
		shopModel.setImageUrl("www.cloudinary.com/swfe/ewfwef");
		shopModel.setIsOpen(true);
		shopModel.setShopAddress("122, south lane");
		shopModel.setShopCity("Pune");
		shopModel.setShopContact("9939299393");
		shopModel.setShopCountry("India");
		shopModel.setShopId(23);
		shopModel.setShopName("Flowery");
		
		shopList.add(shopModel);
		
		FlowersModel flower = mock(FlowersModel.class);
		flower.setId(223);
		flower.setCategory("love");
		flower.setDescription("for you");
		flower.setImageId("sprlfhvwojmxcaxaus7r");
		flower.setName("Teddy");
		flower.setImageUrl("http://res.cloudinary.com/dxpstpk9n/image/upload/v1623596524/sprlfhvwojmxcaxaus7r.jpg");
		flower.setPrice("500");

		List<FlowersModel> flowers = new ArrayList<>();
		flowers.add(flower);

		sales = mock(Sales.class);
		sales.setCustomerModel(customerModel);
		sales.setFlowersModel(flowers);
	}

	public CustomerController get_controller() {
		return _controller;
	}

	public static Customer getCustomer() {
		return customer;
	}

	public static CustomerModel getCustomerModel() {
		return customerModel;
	}

	public static Map<String, String> getImage() {
		return image;
	}

	public static MockMultipartFile getFile() {
		return file;
	}

	public static MaxMinSoldFlower getFlower() {
		return flower;
	}

	public static List<ReportingForChart> getCharts() {
		return charts;
	}

	public static ReportingForChart getFlowerReport() {
		return flowerReport;
	}

	public static ReportingForChart getFlowerReport1() {
		return flowerReport1;
	}

	public static FlowersModel getFlowerModel() {
		return flowerModel;
	}

	public static AuthRequest getAuthRequest() {
		return authRequest;
	}

	public static ShopModel getShopModel() {
		return shopModel;
	}

	public static List<ShopModel> getShopList() {
		return shopList;
	}
	
	

	public static Sales getSales() {
		return sales;
	}

	public static void cleanUp() {
		image = null;
		file = null;
		customer = null;
		customerModel = null;
		flower = null;
		charts = null;
		flowerReport = null;
		flowerReport1 = null;
		flowerModel = null;
		authRequest = null;
	}

}
