package com.online.flowers.service;

import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.online.flowers.dto.FlowerCategoryWiseReport;
import com.online.flowers.dto.MaxMinSoldFlower;
import com.online.flowers.dto.ReportingForChart;
import com.online.flowers.dto.Sales;
import com.online.flowers.model.CategoryWiseReportModel;
import com.online.flowers.model.CustomerModel;
import com.online.flowers.model.DWMReportsModel;
import com.online.flowers.model.ReportsModel;
import com.online.flowers.model.SalesModel;
import com.online.flowers.repo.CategoryWiseReportsRepo;
import com.online.flowers.repo.CustomerRepo;
import com.online.flowers.repo.DWMReportRepo;
import com.online.flowers.repo.ReportRepo;
import com.online.flowers.repo.SalesRepo;

@Service
public class SalesService {

	@Autowired
	private SalesRepo _salesRepo;

	@Autowired
	private CustomerRepo _customerRepo;

	@Autowired
	private ReportRepo _reportRepo;
	
	@Autowired
	private DWMReportRepo _dwmReportRepo;
	
	@Autowired
	private CategoryWiseReportsRepo _categoryWiseReportsRepo;
	
	private boolean testResult = true;

	public boolean recordTransaction(Sales flowersPaymentDone) throws SQLException {
	
		// Record individual transaction
		List<Integer> listToAdd = new ArrayList<>();
		flowersPaymentDone.getFlowersModel().forEach(flower -> {
			SalesModel transaction = new SalesModel();
			transaction.setDate(LocalDate.now());
			transaction.setTime(LocalTime.now());
			transaction.setPrice(flower.getPrice());
			CustomerModel customer = _customerRepo.findByEmail(flowersPaymentDone.getCustomerModel().getEmail());
			transaction.setCustomer(customer);
			transaction.setQuantity(flower.getCount());
			transaction.setFlower(flower);
			SalesModel salesDone = _salesRepo.save(transaction);
			if(salesDone == null) {
				this.testResult = false;
			}
			salesDone = null;
			transaction = null;
			
		});
		//Record product sales
		flowersPaymentDone.getFlowersModel().forEach(flower -> {
			ReportsModel reportsModel = new ReportsModel();
			reportsModel = _reportRepo.findByFlowerId(flower.getId());
			if (reportsModel != null) {
				int quantity = reportsModel.getQuantity() + flower.getCount();
				int price = Integer.parseInt(reportsModel.getPrice()) + Integer.parseInt(flower.getPrice());
				reportsModel.setQuantity(quantity);
				reportsModel.setPrice(String.valueOf(price));

				ReportsModel reports = _reportRepo.save(reportsModel);
				if(reports == null) {
					this.testResult = false;
				}
				reports = null;
				reportsModel = null;
			} else {
				reportsModel = new ReportsModel();
				reportsModel.setFlower(flower);
				reportsModel.setPrice(flower.getPrice());
				reportsModel.setQuantity(flower.getCount());
				ReportsModel reports = _reportRepo.save(reportsModel);
				if(reports == null) {
					this.testResult = false;
				}
				reports = null;
				reportsModel = null;
			}
		});
		
		//Record products per day wise for report generation
		flowersPaymentDone.getFlowersModel().forEach(flower -> {
			DWMReportsModel dwmeportsModel = new DWMReportsModel();
			dwmeportsModel = _dwmReportRepo.findByFlowerId(flower.getId(), LocalDate.now());
			if (dwmeportsModel != null) {
				int quantity = dwmeportsModel.getQuantity() + flower.getCount();
				int price = Integer.parseInt(dwmeportsModel.getPrice()) + Integer.parseInt(flower.getPrice());
				dwmeportsModel.setQuantity(quantity);
				dwmeportsModel.setPrice(String.valueOf(price));

				DWMReportsModel dwmModel = _dwmReportRepo.save(dwmeportsModel);
				if(dwmModel == null) {
					this.testResult = false;
				}
				dwmModel = null;
				dwmeportsModel = null;
			} else {
				dwmeportsModel = new DWMReportsModel();
				dwmeportsModel.setFlower(flower);
				dwmeportsModel.setPrice(flower.getPrice());
				dwmeportsModel.setQuantity(flower.getCount());
				dwmeportsModel.setDate(LocalDate.now());
				DWMReportsModel dwmModel = _dwmReportRepo.save(dwmeportsModel);
				if(dwmModel == null) {
					this.testResult = false;
				}
				dwmModel = null;
				dwmeportsModel = null;
			}
		});
		
		// Record products category wise for showing reports accordingly
		flowersPaymentDone.getFlowersModel().forEach(flower -> {
			CategoryWiseReportModel categoryWiseModel = new CategoryWiseReportModel();
			categoryWiseModel = _categoryWiseReportsRepo.findByCategory(flower.getCategory());
			if (categoryWiseModel != null) {
				int quantity = categoryWiseModel.getQuantity() + flower.getCount();
				int price = Integer.parseInt(categoryWiseModel.getPrice()) + Integer.parseInt(flower.getPrice());
				categoryWiseModel.setQuantity(quantity);
				categoryWiseModel.setPrice(String.valueOf(price));

				CategoryWiseReportModel catModel = _categoryWiseReportsRepo.save(categoryWiseModel);
				if(catModel == null) {
					this.testResult = false;
				}
				catModel = null;
				categoryWiseModel = null;
			} else {
				categoryWiseModel = new CategoryWiseReportModel();
				categoryWiseModel.setCategory(flower.getCategory());
				categoryWiseModel.setFlower(flower);
				categoryWiseModel.setPrice(flower.getPrice());
				categoryWiseModel.setQuantity(flower.getCount());
				CategoryWiseReportModel catModel = _categoryWiseReportsRepo.save(categoryWiseModel);
				if(catModel == null) {
					this.testResult = false;
				}
				catModel = null;
				categoryWiseModel = null;
			}
		});
		
		return this.testResult;

	}

	public MaxMinSoldFlower getMostSoldFlower() throws SQLException {

		MaxMinSoldFlower maxMinSoldFlower = new MaxMinSoldFlower();

		ReportsModel salesMostSold = _reportRepo.findMostSoldProduct();
		maxMinSoldFlower.setPrice(salesMostSold.getPrice());
		maxMinSoldFlower.setQuantity(salesMostSold.getQuantity());
		maxMinSoldFlower.setName(salesMostSold.getFlower().getName());
		maxMinSoldFlower.setCategory(salesMostSold.getFlower().getCategory());
		maxMinSoldFlower.setDescription(salesMostSold.getFlower().getDescription());
		maxMinSoldFlower.setImageUrl(salesMostSold.getFlower().getImageUrl());
		maxMinSoldFlower.setId(salesMostSold.getFlower().getId());

		return maxMinSoldFlower;

	}
	
	public MaxMinSoldFlower getLeastSoldFlower() throws SQLException {

		MaxMinSoldFlower maxMinSoldFlower = new MaxMinSoldFlower();

		ReportsModel salesMostSold = _reportRepo.findLeastSoldProduct();
		maxMinSoldFlower.setPrice(salesMostSold.getPrice());
		maxMinSoldFlower.setQuantity(salesMostSold.getQuantity());
		maxMinSoldFlower.setName(salesMostSold.getFlower().getName());
		maxMinSoldFlower.setCategory(salesMostSold.getFlower().getCategory());
		maxMinSoldFlower.setDescription(salesMostSold.getFlower().getDescription());
		maxMinSoldFlower.setImageUrl(salesMostSold.getFlower().getImageUrl());
		maxMinSoldFlower.setId(salesMostSold.getFlower().getId());

		return maxMinSoldFlower;

	}
	
	public List<ReportingForChart> getDailyReport() {
		List<DWMReportsModel> todaysSales = _dwmReportRepo.getByDate(LocalDate.now());
		if(todaysSales != null) {
			List<ReportingForChart> saleOfToday = new ArrayList<>();
			todaysSales.forEach(flower -> {
				ReportingForChart reportingForTodayChart = new ReportingForChart();
				reportingForTodayChart.setId(flower.getFlower().getId());
				reportingForTodayChart.setName(flower.getFlower().getName());
				reportingForTodayChart.setQuantity(flower.getQuantity());
				reportingForTodayChart.setPrice(flower.getPrice());
				saleOfToday.add(reportingForTodayChart);
				reportingForTodayChart = null;
			});
			
			return saleOfToday;
		}
		return null;		
	}
	
	public List<ReportingForChart> getWeeklyReport() {
		List<DWMReportsModel> todaysSales = _dwmReportRepo.getByDateFromTo(LocalDate.now().minus(1, ChronoUnit.WEEKS), LocalDate.now());
		if(todaysSales != null) {
			List<ReportingForChart> saleOfToday = new ArrayList<>();
			todaysSales.forEach(flower -> {
				ReportingForChart reportingForTodayChart = new ReportingForChart();
				reportingForTodayChart.setId(flower.getFlower().getId());
				reportingForTodayChart.setName(flower.getFlower().getName());
				reportingForTodayChart.setQuantity(flower.getQuantity());
				reportingForTodayChart.setPrice(flower.getPrice());
				saleOfToday.add(reportingForTodayChart);
				reportingForTodayChart = null;
			});
			
			return saleOfToday;
		}
		return null;		
	}
	
	public List<ReportingForChart> getMonthlyReport() {
		List<DWMReportsModel> todaysSales = _dwmReportRepo.getByDateFromTo(LocalDate.now().minus(1, ChronoUnit.MONTHS), LocalDate.now());
		if(todaysSales != null) {
			List<ReportingForChart> saleOfToday = new ArrayList<>();
			todaysSales.forEach(flower -> {
				ReportingForChart reportingForTodayChart = new ReportingForChart();
				reportingForTodayChart.setId(flower.getFlower().getId());
				reportingForTodayChart.setName(flower.getFlower().getName());
				reportingForTodayChart.setQuantity(flower.getQuantity());
				reportingForTodayChart.setPrice(flower.getPrice());
				saleOfToday.add(reportingForTodayChart);
				reportingForTodayChart = null;
			});
			
			return saleOfToday;
		}
		return null;		
	}
	
	
	public List<FlowerCategoryWiseReport> getCategoryWiseReport() {
		List<CategoryWiseReportModel> reportsModels = _categoryWiseReportsRepo.findAll();
		List<FlowerCategoryWiseReport> categoryWiseReports = new ArrayList<>();
		Optional.ofNullable(reportsModels).ifPresent(flower -> {			
			flower.forEach(flowerItem -> {
				FlowerCategoryWiseReport report = new FlowerCategoryWiseReport();
				report.setId(flowerItem.getFlower().getId());
				report.setCategory(flowerItem.getFlower().getCategory());
				report.setDescription(flowerItem.getFlower().getDescription());
				report.setName(flowerItem.getFlower().getName());
				report.setPrice(flowerItem.getFlower().getPrice());
				report.setQuantity(flowerItem.getQuantity());
				categoryWiseReports.add(report);
				report = null;
			});
		});
		return categoryWiseReports;
	}
	
	

}
