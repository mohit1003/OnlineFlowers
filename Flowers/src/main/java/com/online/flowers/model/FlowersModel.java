package com.online.flowers.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class FlowersModel {
	
	
	@Id
	@GeneratedValue( strategy = GenerationType.IDENTITY)
	private int id;
	
	private String name;
	
	private String imageUrl;
	
//	private byte[] photo;
	
	private String imageId;
	
	private String category;
		
	private String price;
	
	private String description;
	
	
	public FlowersModel() {
		super();
	}
	

//	public FlowersModel(String name, byte[] photo, String category, String price, String description) {
//		super();
//		this.name = name;
//		this.photo = photo;
//		this.category = category;
//		this.price = price;
//		this.description = description;
//	}
	
	
	

	public String getName() {
		return name;
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


	public void setName(String name) {
		this.name = name;
	}


	public int getId() {
		return id;
	}

	public void setId(int id) {
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
	
	
	
	
	
	
}
