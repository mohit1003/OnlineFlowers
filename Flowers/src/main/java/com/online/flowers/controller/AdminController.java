package com.online.flowers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.dto.CustomerDto;
import com.online.flowers.dto.CustomerRegionWiseReport;
import com.online.flowers.dto.FlowerCategoryWiseReport;
import com.online.flowers.dto.MaxMinSoldFlower;
import com.online.flowers.dto.ReportingForChart;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.service.ClaudinaryService;
import com.online.flowers.service.CustomerService;
import com.online.flowers.service.FlowersService;
import com.online.flowers.service.SalesService;
import com.online.flowers.service.ShopService;

@RestController
@RequestMapping(path = "/admin")
@PreAuthorize("hasAnyRole('ADMIN')")
@CrossOrigin(origins = "*")
public class AdminController {
	
	@Autowired
	private FlowersService flowersService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private SalesService _salesService;


	@Autowired
	private ClaudinaryService cloudinaryService;


	@Autowired
	private CustomerService _customerService;
	
	@PostMapping(value = "/add")
	public ResponseEntity<String> saveFlowerImage(@RequestParam("flowerImage") MultipartFile flower,
			@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("category") String category, @RequestParam("price") String price) {

		String message = flowersService.saveImage(flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>("Image not valid", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED);
		}
	}


	@PutMapping(value = "/update")
	public ResponseEntity<String> updateFlower(@RequestParam("id") String id,
			@RequestParam("flowerImage") MultipartFile flower, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("price") String price) {

		String message = flowersService.updateFlower(id, flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>("Image not valid", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>("Image is uploaded", HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/updateWithoutImage")
	public ResponseEntity<String> updateFlowerWithoutImage(@RequestBody FlowersModel flower) {
		String message = flowersService.updateFlowerWithoutImage(flower);

		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>("Image not valid", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>("Image is uploaded/modified", HttpStatus.OK);
		}

	}
	
	@DeleteMapping(value = "/delete")
	public ResponseEntity<String> delete(@RequestBody Map<String, String> id) {
		String flowerId = id.get("id");
		if (!id.get("id").isEmpty()) {
			try {
				cloudinaryService.delete(flowersService.getPublicIdById(flowerId));
				flowersService.deleteFlower(flowerId);
				return new ResponseEntity<String>("Image deleted", HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity<String>("Error deleting image", HttpStatus.CONFLICT);

	}
	
	@PostMapping(value = "/addShop")
	public ResponseEntity<String> addShop(@RequestParam("shopImage") MultipartFile shopImage,
			@RequestParam("shopName") String shopName, @RequestParam("shopCity") String shopCity,
			@RequestParam("shopCountry") String shopCountry, @RequestParam("shopAddress") String shopAddress,
			@RequestParam("isOpen") String isOpen, @RequestParam("shopContact") String shopContact) {
		String message = shopService.saveShop(shopImage, shopName, shopCity, shopCountry, shopAddress, isOpen,
				shopContact);
		if (message.equals("Image not valid")) {
			return new ResponseEntity<String>("Shop not added", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<String>("Shop added successfully", HttpStatus.CREATED);
		}
	}
	
	@GetMapping(value = "/getAllCustomers")
	public ResponseEntity<?> getAllCustomers() {
		List<CustomerDto> customers = _customerService.getAllCustomers();

		if (customers.isEmpty()) {
			return new ResponseEntity<String>("No customers returned", HttpStatus.CONFLICT);
		}

		return new ResponseEntity<List<CustomerDto>>(customers, HttpStatus.OK);

	}
	
	@GetMapping(value = "/getMostSoldProduct")
	public ResponseEntity<?> getMostSoldProduct() {
		MaxMinSoldFlower maxSoldFlower;
		try {
			maxSoldFlower = _salesService.getMostSoldFlower();
			if (maxSoldFlower != null) {
				return new ResponseEntity<MaxMinSoldFlower>(maxSoldFlower, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
		}

	}

	@GetMapping(value = "/getLeastSoldProduct")
	public ResponseEntity<?> getLeastSoldProduct() {
		MaxMinSoldFlower minSoldFlower;
		try {
			minSoldFlower = _salesService.getLeastSoldFlower();
			if (minSoldFlower != null) {
				return new ResponseEntity<MaxMinSoldFlower>(minSoldFlower, HttpStatus.OK);
			} else {
				return new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			return new ResponseEntity<String>("Error", HttpStatus.CONFLICT);
		}

	}

	@GetMapping(value = "/getTodaysSalesReport")
	public ResponseEntity<?> getTodaysSalesReport() {
		List<ReportingForChart> reportingForChartToday = _salesService.getDailyReport();
		if (reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error generating daily chart", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = "/getLastWeekSalesReport")
	public ResponseEntity<?> getLastWeekSalesReport() {
		List<ReportingForChart> reportingForChartToday = _salesService.getWeeklyReport();
		if (reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error generating Weekly chart", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = "/getMonthlySalesReport")
	public ResponseEntity<?> getMonthlySalesReport() {
		List<ReportingForChart> reportingForChartToday = _salesService.getMonthlyReport();
		if (reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error generating Monthly chart", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = "/getFlowerCategoryWiseReport")
	public ResponseEntity<?> getFlowerCategoryWiseReport() {
		List<FlowerCategoryWiseReport> categoryWisereport = _salesService.getCategoryWiseReport();
		if (categoryWisereport != null) {
			return new ResponseEntity<List<FlowerCategoryWiseReport>>(categoryWisereport, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("Error generating category-wise chart", HttpStatus.CONFLICT);
		}
	}

	@GetMapping(value = "/getCustomersCityWise")
	public ResponseEntity<?> getCustomerRegionWiseData() {
		List<CustomerRegionWiseReport> customersCityWise = _customerService.getCustomerRegionWiseData();

		if (!customersCityWise.isEmpty()) {
			return new ResponseEntity<List<CustomerRegionWiseReport>>(customersCityWise, HttpStatus.OK);
		} else {
			return new ResponseEntity<String>("No customer data returned", HttpStatus.CONFLICT);

		}
	}


}
