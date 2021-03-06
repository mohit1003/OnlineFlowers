package com.online.flowers.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CategoryWiseReportModel {
	@Id
	private String category;
	
	private Integer quantity;
	
	private String price;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "flower_id_fk")
	private FlowersModel flower;

	

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public FlowersModel getFlower() {
		return flower;
	}

	public void setFlower(FlowersModel flower) {
		this.flower = flower;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	} 
	
	
	
	
	
	

}