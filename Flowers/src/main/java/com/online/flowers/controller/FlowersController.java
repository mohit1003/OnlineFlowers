package com.online.flowers.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.online.flowers.dto.Customer;
import com.online.flowers.dto.FlowerCategoryWiseReport;
import com.online.flowers.dto.MaxMinSoldFlower;
import com.online.flowers.dto.Message;
import com.online.flowers.dto.ReportingForChart;
import com.online.flowers.dto.Sales;
import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.model.SalesModel;
import com.online.flowers.model.ShopModel;
import com.online.flowers.repo.CustomerRepo;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.repo.SalesRepo;
import com.online.flowers.repo.ShopRepo;
import com.online.flowers.service.ClaudinaryService;
import com.online.flowers.service.CustomerService;
import com.online.flowers.service.FlowersService;
import com.online.flowers.service.ImageService;
import com.online.flowers.service.SalesService;
import com.online.flowers.service.ShopService;
import com.online.flowers.util.AuthRequest;
import com.online.flowers.util.JwtUtil;

@RestController
@CrossOrigin(origins = "*")
public class FlowersController {

	@Autowired
	private FlowersService flowersService;
	
	@Autowired
	private ShopService shopService;
	
	@Autowired
	private SalesService _salesService;

	@Autowired
	private FlowersRepo _repo;
	
	@Autowired
	private CustomerRepo _custRepo;
	
	@Autowired
	private ShopRepo _shoprepo;

	@Autowired
	private ClaudinaryService cloudinaryService;

	@Autowired
	private JwtUtil jwtutil;

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private CustomerService _customerService;
	
	
	
	
	int registerFlag = 0;
	
	int loginFlag = 0;
	
	@PostMapping(value = "/register")
	public ResponseEntity<?> registerUser(@RequestBody CustomerModel customer) {
		Optional.ofNullable(customer).ifPresent(customerToSave -> {
			if(_custRepo.existsById(customer.getEmail())) {
				this.registerFlag = 2;
			}
			else {
				_custRepo.save(customerToSave);
				this.registerFlag = 1;
			}
		});
		
		if(registerFlag == 1) {
			return new ResponseEntity(new Message("User is registered"), HttpStatus.CREATED);
		}
		else {
			registerFlag = 0;
			return new ResponseEntity(new Message("User not registered/ already exists"), HttpStatus.CONFLICT);
		}
		
		
	}
	
	@PostMapping(value = "/login")
	public ResponseEntity<?> userLogin(@RequestBody CustomerModel customer) {
		Optional.ofNullable(customer).ifPresent(customerToSave -> {
			if(_custRepo.existsById(customer.getEmail())) {
					this.loginFlag = 1;
			}
		});
		
		if(loginFlag == 1) {
			return new ResponseEntity(new Message("user is logged in"), HttpStatus.OK);
		}
		loginFlag = 0;
		return new ResponseEntity(new Message("Invalid Username or Password"), HttpStatus.CONFLICT);
	}


	@PostMapping(value = "/authenticate")
	public String generateToken(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword()));
		} catch (Exception e) {
			throw new Exception("Invalid username/password");
		}

		return jwtutil.generateToken(authRequest.getEmail());
	}
	
	@PostMapping(value = "/getUserByEmail")
	public Optional<ResponseEntity<Customer>> getCustomerByEmail(@RequestBody String email) {
		return Optional.ofNullable(new ResponseEntity<Customer>(_customerService.ConvertToCustomerDtoAndSend(email), HttpStatus.OK));
	} 

	@PostMapping(value = "/add")
	public ResponseEntity<?> saveFlowerImage(@RequestParam("flowerImage") MultipartFile flower,
			@RequestParam("name") String name, @RequestParam("description") String description,
			@RequestParam("category") String category, @RequestParam("price") String price) {

		String message = flowersService.saveImage(flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new Message("Image is uploaded"), HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/update")
	public ResponseEntity<?> updateFlower(@RequestParam("id") String id,
			@RequestParam("flowerImage") MultipartFile flower, @RequestParam("name") String name,
			@RequestParam("description") String description, @RequestParam("category") String category,
			@RequestParam("price") String price) {

		String message = flowersService.updateFlower(id, flower, name, category, price, description);
		if (message.equals("Image not valid")) {
			return new ResponseEntity(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new Message("Image is uploaded"), HttpStatus.CREATED);
		}
	}

	@PutMapping(value = "/updateWithoutImage")
	public ResponseEntity<String> updateFlowerWithoutImage(@RequestBody FlowersModel flower) {
		String message = flowersService.updateFlowerWithoutImage(flower);

		if (message.equals("Image not valid")) {
			return new ResponseEntity(new Message("Image not valid"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new Message("Image is uploaded/modified"), HttpStatus.OK);
		}

	}

	@GetMapping(value = "/getAllFlowers")
	public ResponseEntity<List<FlowersModel>> list() {
		List<FlowersModel> list = flowersService.list();
		return new ResponseEntity<List<FlowersModel>>(list, HttpStatus.OK);
	}

	@DeleteMapping(value = "delete")
	public ResponseEntity<?> delete(@RequestBody Map<String, String> id) {
		String flowerId = id.get("id");
		if (!id.get("id").isEmpty()) {
			try {
				cloudinaryService.delete(flowersService.getPublicIdById(flowerId));
				flowersService.deleteFlower(flowerId);
				return new ResponseEntity(new Message("Image deleted"), HttpStatus.OK);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return new ResponseEntity(new Message("Error deleting image"), HttpStatus.CONFLICT);

	}

	public Optional<FlowersModel> getImage(Integer id) {
		return _repo.findById(id);
	}
	
	
	
	@PostMapping(value = "/addShop")
	public ResponseEntity<?> addShop(@RequestParam("shopImage") MultipartFile shopImage, 
			@RequestParam("shopName") String shopName, @RequestParam("shopCity") String shopCity,
			@RequestParam("shopCountry") String shopCountry, @RequestParam("shopAddress") String shopAddress,
			@RequestParam("isOpen") String isOpen, @RequestParam("shopContact") String shopContact){
		String message = shopService.saveShop(shopImage, shopName, shopCity, shopCountry, shopAddress, isOpen, shopContact);
		if (message.equals("Image not valid")) {
			return new ResponseEntity(new Message("Shop not added"), HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity(new Message("Shop added successfully"), HttpStatus.CREATED);
		}		
	}
	
	@GetMapping(value = "/getAllShops")
	public List<ShopModel> getShop(){
		return _shoprepo.findAll();
	}
	
	@PostMapping(value = "/pay")
	public ResponseEntity<?> makeTransaction(@RequestBody Sales flowersPaymentDone) {
		try {
			_salesService.recordTransaction(flowersPaymentDone);
			return new ResponseEntity(new Message("Payment Success"), HttpStatus.CREATED);
		} catch (SQLException e) {
			return new ResponseEntity(new Message("Payment falied"), HttpStatus.CONFLICT);
		} catch( Exception ex ) {
			return new ResponseEntity(new Message("Payment falied"), HttpStatus.CONFLICT);
		}
		
	}
	
	@GetMapping(value = "/getMostSoldProduct")
	public ResponseEntity<?> getMostSoldProduct() {
		MaxMinSoldFlower maxSoldFlower;
		try {
			maxSoldFlower = _salesService.getMostSoldFlower();
			if(maxSoldFlower != null) {
				return new ResponseEntity<MaxMinSoldFlower>(maxSoldFlower, HttpStatus.OK);
			}
			else {
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
			if(minSoldFlower != null) {
				return new ResponseEntity<MaxMinSoldFlower>(minSoldFlower, HttpStatus.OK);
			}
			else {
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
		if(reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error generating daily chart", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/getLastWeekSalesReport")
	public ResponseEntity<?> getLastWeekSalesReport() {
		List<ReportingForChart> reportingForChartToday = _salesService.getWeeklyReport();
		if(reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error generating Weekly chart", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/getMonthlySalesReport")
	public ResponseEntity<?> getMonthlySalesReport() {
		List<ReportingForChart> reportingForChartToday = _salesService.getMonthlyReport();
		if(reportingForChartToday != null) {
			return new ResponseEntity<List<ReportingForChart>>(reportingForChartToday, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error generating Monthly chart", HttpStatus.CONFLICT);
		}
	}
	
	@GetMapping(value = "/getFlowerCategoryWiseReport")
	public ResponseEntity<?> getFlowerCategoryWiseReport() {
		List<FlowerCategoryWiseReport> categoryWisereport = _salesService.getCategoryWiseReport();
		if(categoryWisereport != null) {
			return new ResponseEntity<List<FlowerCategoryWiseReport>>(categoryWisereport, HttpStatus.OK);
		}
		else {
			return new ResponseEntity<String>("Error generating category-wise chart", HttpStatus.CONFLICT);
		}
	}
	
	


}
