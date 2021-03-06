package com.online.flowers.model;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SalesModel {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer transactionId;
	
	private Integer quantity;
	
	private LocalDate date;
	
	private LocalTime time;
	
	private String price;
	
	@OneToOne(targetEntity = CustomerModel.class)
	@JoinColumn(name = "custSales_fk", referencedColumnName = "email")
	private CustomerModel customer;
	
	@ManyToOne(cascade = CascadeType.DETACH)
	@JoinColumn(name = "flowerSales_fk")
	private FlowersModel flower;	
	

	public Integer getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(Integer transactionId) {
		this.transactionId = transactionId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public LocalTime getTime() {
		return time;
	}
	
	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}
	

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public CustomerModel getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerModel customer) {
		this.customer = customer;
	}

	public FlowersModel getFlower() {
		return flower;
	}

	public void setFlower(FlowersModel flower) {
		this.flower = flower;
	}

	
	

	

	

	

	
	
	
	
	
	
	
	
	
	
	

	
	

	
}
