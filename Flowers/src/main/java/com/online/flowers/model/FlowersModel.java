package com.online.flowers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class FlowersModel {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.AUTO)
	private Integer id;
	
	private String name;
	
	private String imageUrl;
	
//	private byte[] photo;
	
	private String imageId;
	
	private String category;
		
	private String price;
	
	private String description;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flower", cascade ={CascadeType.DETACH, CascadeType.REMOVE})
    private List<SalesModel> sales;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flower",  cascade ={CascadeType.DETACH, CascadeType.REMOVE})
    private List<ReportsModel> reports;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flower", cascade ={CascadeType.DETACH, CascadeType.REMOVE})
    private List<DWMReportsModel> dwmReports;
	
	@JsonIgnore
	@OneToMany(mappedBy = "flower", cascade ={CascadeType.DETACH, CascadeType.REMOVE})
    private List<CategoryWiseReportModel> categoryReports;
	
	@Transient
	private Integer count = 1;
	
	
	public FlowersModel() {
		super();
	}
	
	

	public String getName() {
		return name;
	}
	
	


	public FlowersModel(Integer id, String name, String category, String price, String description) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
		this.description = description;
	}


	public FlowersModel(String name, String imageUrl, String imageId, String category, String price,
			String description) {
		super();
		this.name = name;
		this.imageUrl = imageUrl;
		this.imageId = imageId;
		this.category = category;
		this.price = price;
		this.description = description;
	}



	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public String getImageUrl() {
		return imageUrl;
	}



	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}



	public String getImageId() {
		return imageId;
	}



	public void setImageId(String imageId) {
		this.imageId = imageId;
	}



	public String getCategory() {
		return category;
	}



	public void setCategory(String category) {
		this.category = category;
	}



	public String getPrice() {
		return price;
	}



	public void setPrice(String price) {
		this.price = price;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public List<SalesModel> getSales() {
		return sales;
	}



	public void setSales(List<SalesModel> sales) {
		this.sales = sales;
	}



	


	public List<ReportsModel> getReports() {
		return reports;
	}



	public void setReports(List<ReportsModel> reports) {
		this.reports = reports;
	}



	public List<DWMReportsModel> getDwmReports() {
		return dwmReports;
	}



	public void setDwmReports(List<DWMReportsModel> dwmReports) {
		this.dwmReports = dwmReports;
	}



	public List<CategoryWiseReportModel> getCategoryReports() {
		return categoryReports;
	}



	public void setCategoryReports(List<CategoryWiseReportModel> categoryReports) {
		this.categoryReports = categoryReports;
	}



	public Integer getCount() {
		return count;
	}



	public void setCount(Integer count) {
		this.count = count;
	}



	public void setName(String name) {
		this.name = name;
	}


	
	


	
	
	
	
	
	
	
	
	
}
