package com.online.flowers.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 
 * 
 * @author mohitkulkarni
 * 
 * This class is for reports to be generated 
 *  *
 */

@Entity
public class ReportsModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "flower_id_fk")
	private FlowersModel flower; 
	
	private Integer quantity;
	
	private String price;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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