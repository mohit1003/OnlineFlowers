package com.online.flowers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 
 * @author mohitkulkarni
 * 
 * This class is for daily, monthly charts displayed on the client
 *
 */

public class ReportingForChart {
	
	private Integer id;
	
	private String name;
	
	private String price;
	
	@JsonProperty(value = "count")
	private Integer quantity;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	

}
