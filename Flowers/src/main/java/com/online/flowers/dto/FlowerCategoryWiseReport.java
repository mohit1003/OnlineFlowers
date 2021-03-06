/**
 * 
 * This DTO is for reports shown at angular client product category wise
 */

package com.online.flowers.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public class FlowerCategoryWiseReport {
	private Integer id;
	
	private String name;
	
	private String description;
	
	private String category;
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	
	
	

}
