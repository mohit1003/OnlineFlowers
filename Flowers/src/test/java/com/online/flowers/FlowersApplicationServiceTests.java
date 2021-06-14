package com.online.flowers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.context.junit4.SpringRunner;

import com.online.flowers.dto.Customer;
import com.online.flowers.dto.CustomerRegionWiseReport;
import com.online.flowers.dto.FlowerCategoryWiseReport;
import com.online.flowers.dto.MaxMinSoldFlower;
import com.online.flowers.dto.ReportingForChart;
import com.online.flowers.dto.Sales;
import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.FlowersModel;
import com.online.flowers.repo.CategoryWiseReportsRepo;
import com.online.flowers.repo.CustomerRepo;
import com.online.flowers.repo.DWMReportRepo;
import com.online.flowers.repo.FlowersRepo;
import com.online.flowers.repo.ReportRepo;
import com.online.flowers.repo.SalesRepo;
import com.online.flowers.service.ClaudinaryService;
import com.online.flowers.service.CustomerService;
import com.online.flowers.service.DtoMapperService;
import com.online.flowers.service.FlowersService;
import com.online.flowers.service.SalesService;
import com.online.flowers.service.ShopService;

class CustomerRegionWiseReportTest implements CustomerRegionWiseReport {
	int emailCount;
	String city;

	CustomerRegionWiseReportTest(int emailCount, String city) {
		this.emailCount = emailCount;
		this.city = city;
	}

	@Override
	public int getEmailCount() {
		return 1;
	}

	@Override
	public String getCity() {
		return "London";
	}
}

@SpringBootTest
@RunWith(SpringRunner.class)
class FlowersApplicationServiceTests {

	@Autowired
	private CustomerService customerService;

	@MockBean
	private DtoMapperService _mapperService;

	@MockBean
	private ClaudinaryService _cloudinaryService;

	@MockBean
	private SalesService _salesService;

	@MockBean
	private FlowersService _flowersService;

	@MockBean
	private ShopService _shopService;

	@MockBean
	private CustomerRepo _customerRepo;

	@MockBean
	private FlowersRepo _repo;

	@MockBean
	private SalesRepo _salesRepo;

	@MockBean
	private ReportRepo _reportRepo;

	@MockBean
	private DWMReportRepo _dwmReportRepo;

	@MockBean
	private CategoryWiseReportsRepo _categoryWiseReportsRepo;

	static Customer customer;

	static CustomerModel customerModel;

	static Map<String, String> image;

	static MockMultipartFile file;

	static MaxMinSoldFlower flower;

	static List<ReportingForChart> charts;

	static ReportingForChart flowerReport;

	static ReportingForChart flowerReport1;

	static FlowersModel flowerModel;

	public void setUpCustomer() {
		customer = new Customer("1@2", "MR", "Mohit", "Kulkarni", "123, High street, Brentford, London", "London", "UK",
				"$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeu0S", "9921918100");

		customerModel = new CustomerModel("1@2", "MR", "Mohit", "Kulkarni", "123, High street, Brentford, London",
				"London", "UK", "$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeu0S", "9921918100");
	}

	public static void setUpCloudinaryMap() {
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
	}

	public void setUpFlower() {
		flowerModel = mock(FlowersModel.class);
		flower.setId(223);
		flower.setCategory("love");
		flower.setDescription("for you");
		flower.setName("Teddy");
		flower.setImageUrl("http://res.cloudinary.com/dxpstpk9n/image/upload/v1623596524/sprlfhvwojmxcaxaus7r.jpg");
		flower.setPrice("500");
	}

	public void setUpmaxMinFlowers() {
		flower = mock(MaxMinSoldFlower.class);
		flower.setId(223);
		flower.setCategory("love");
		flower.setDescription("for you");
		flower.setName("Teddy");
		flower.setImageUrl("http://res.cloudinary.com/dxpstpk9n/image/upload/v1623596524/sprlfhvwojmxcaxaus7r.jpg");
		flower.setPrice("500");
		flower.setQuantity(5);
	}

	public void setUpReportingData() {
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
	}

	@Test
	public void getAllUsersTest() {
		setUpCustomer();

		when(_customerRepo.findAll()).thenReturn(Stream
				.of(FlowersApplicationServiceTests.customerModel,
						new CustomerModel("1@3", "MR", "Mavit", "Kulk", "123, High street, Kolkata", "Kolkata", "India",
								"$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeuU8", "8884888288"))
				.collect(Collectors.toList()));
		assertEquals(2, customerService.getAllCustomers().size());
	}

	@Test
	public void getCustomerRegionWiseDataTest() {
		when(_customerRepo.getCustomersGroupByCity())
				.thenReturn(Stream.of(new CustomerRegionWiseReportTest(1, "London")).collect(Collectors.toList()));

		assertEquals(1, customerService.getCustomerRegionWiseData().size());
	}

	@Test
	public void convertToCustomerDtoTest() {
		setUpCustomer();
		Optional<CustomerModel> customer = Optional
				.ofNullable(new CustomerModel("1@2", "MR", "Mohit", "Kulkarni", "123, High street, Brentford, London",
						"London", "UK", "$2a$10$KLQUgWpeYzNT8q48c226I.5BKJZ41TZaKkJU/4H.3FWyjSUEAeu0S", "9921918100"));

		Customer customerToCompare = FlowersApplicationServiceTests.customer;

		when(_mapperService.convertToCustomerDto(customer)).thenReturn(customerToCompare);
		assertEquals(customerToCompare, _mapperService.convertToCustomerDto(customer));
	}

	@Test
	public void recordTransactionTest() throws SQLException {
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

		Sales sales = new Sales();
		sales.setCustomerModel(customerModel);
		sales.setFlowersModel(flowers);

		try {
			when(_salesService.recordTransaction(sales)).thenReturn(true);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(true, _salesService.recordTransaction(sales));
	}

	@Test
	public void getMostSoldFlowerTest() throws SQLException {
		setUpmaxMinFlowers();
 
		when(_salesService.getMostSoldFlower()).thenReturn(flower);
		assertEquals(flower, _salesService.getMostSoldFlower());

	}

	@Test
	public void getLeastSoldFlower() throws SQLException {
		setUpmaxMinFlowers();

		when(_salesService.getLeastSoldFlower()).thenReturn(flower);
		assertEquals(flower, _salesService.getLeastSoldFlower());

	}

	@Test
	public void getDailyReportTest() {
		setUpReportingData();

		when(_salesService.getDailyReport()).thenReturn(charts);
		assertEquals(charts, _salesService.getDailyReport());
	}

	@Test
	public void getWeeklyReportTest() {
		setUpReportingData();

		when(_salesService.getWeeklyReport()).thenReturn(charts);
		assertEquals(charts, _salesService.getWeeklyReport());
	}

	@Test
	public void getMonthlyReportTest() {
		setUpReportingData();

		when(_salesService.getMonthlyReport()).thenReturn(charts);
		assertEquals(charts, _salesService.getMonthlyReport());
	}

	@Test
	public void getCategoryWiseReport() {
		List<FlowerCategoryWiseReport> charts = new ArrayList<>();
		FlowerCategoryWiseReport flower = mock(FlowerCategoryWiseReport.class);
		flower.setId(223);
		flower.setName("Teddy");
		flower.setPrice("500");
		flower.setQuantity(3);

		FlowerCategoryWiseReport flower2 = mock(FlowerCategoryWiseReport.class);
		flower.setId(233);
		flower.setName("Whity");
		flower.setPrice("200");
		flower.setQuantity(8);

		charts.add(flower2);
		charts.add(flower);

		when(_salesService.getCategoryWiseReport()).thenReturn(charts);
		assertEquals(charts, _salesService.getCategoryWiseReport());
	}

	@Test
	public void uploadTest() throws IOException {
		setUpCloudinaryMap();

		when(_cloudinaryService.upload(file)).thenReturn(image);
		assertEquals(image, _cloudinaryService.upload(file));
	}

	@Test
	public void deleteTest() throws IOException {
		setUpCloudinaryMap();

		when(_cloudinaryService.delete("e4d788ab6c7f87368019c2b34f4e5c9e")).thenReturn(image);
		assertEquals(image, _cloudinaryService.delete("e4d788ab6c7f87368019c2b34f4e5c9e"));
	}

	// Test from FlowersService.java
	@Test
	public void saveImageTest() {
		String name = "Symphy";
		String category = "Sympathy";
		String price = "500";
		String description = "Sympathy white boquet";

		when(_flowersService.saveImage(file, name, category, price, description)).thenReturn("ok");
		assertEquals("ok", _flowersService.saveImage(file, name, category, price, description));

	}

	@Test
	public void updateImageTest() {
		String id = "e4d788ab6c7f87368019c2b34f4e5c9e";
		String name = "Symphy";
		String category = "Sympathy";
		String price = "500";
		String description = "Sympathy white boquet";

		when(_flowersService.updateFlower(id, file, name, category, price, description)).thenReturn("ok");
		assertEquals("ok", _flowersService.updateFlower(id, file, name, category, price, description));

	}

	@Test
	public void updateFlowerWithoutImageTest() {
		setUpFlower();

		when(_flowersService.updateFlowerWithoutImage(flowerModel)).thenReturn("ok");
		assertEquals("ok", _flowersService.updateFlowerWithoutImage(flowerModel));
	}

	@Test
	public void getPublicIdByIdTest() {
		when(_flowersService.getPublicIdById("223")).thenReturn("e4d788ab6c7f87368019c2b34f4e5c9e");
		assertEquals("e4d788ab6c7f87368019c2b34f4e5c9e", _flowersService.getPublicIdById("223"));
	}

	@Test
	public void listTest() {
		List<FlowersModel> flowerList = new ArrayList<>();
		flowerList.add(flowerModel);

		when(_flowersService.list()).thenReturn(flowerList);
		assertEquals(flowerList, _flowersService.list());

		flowerList = null;
	}

	@Test
	public void saveShopTest() {
		String shopName = "Symphy";
		String shopCity = "Pune";
		String shopCountry = "India";
		String shopAddress = "112, East lane, MG Road";
		String isOpen = "open";
		String shopContact = "8848477372";
		
		when(_shopService.saveShop(file, shopName, shopCity, shopCountry, shopAddress, isOpen, shopContact)).thenReturn("ok");
		assertEquals("ok", _shopService.saveShop(file, shopName, shopCity, shopCountry, shopAddress, isOpen, shopContact));

	}

	@AfterAll
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
	}

}
