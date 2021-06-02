package com.online.flowers.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ShopModel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int shopId;
	@NotEmpty
	@Size(max = 30)
	private String shopName;
	@NotEmpty
	@Size(max = 30)
	private String shopCity;
	@NotEmpty
	@Size(max = 30)
	private String shopCountry;
	@NotEmpty
	@Size(max = 30)
	private String shopAddress;
	@NotNull
	private Boolean isOpen;
	@NotEmpty
	private String imageUrl;
	
	@NotEmpty
	private String imageId;
	
	@NotEmpty
	@Size(min = 10, max = 10)
	private String shopContact;
	
	public int getShopId() {
		return shopId;
	}
	public void setShopId(int shopId) {
		this.shopId = shopId;
	}
	public String getShopName() {
		return shopName;
	}
	public void setShopName(String shopName) {
		this.shopName = shopName;
	}
	public String getShopCity() {
		return shopCity;
	}
	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}
	public String getShopCountry() {
		return shopCountry;
	}
	public void setShopCountry(String shopCountry) {
		this.shopCountry = shopCountry;
	}
	public String getShopAddress() {
		return shopAddress;
	}
	public void setShopAddress(String shopAddress) {
		this.shopAddress = shopAddress;
	}
	public boolean isOpen() {
		return isOpen;
	}
	public void setOpen(boolean isOpen) {
		this.isOpen = isOpen;
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
	public Boolean getIsOpen() {
		return isOpen;
	}
	public void setIsOpen(Boolean isOpen) {
		this.isOpen = isOpen;
	}
	public String getShopContact() {
		return shopContact;
	}
	public void setShopContact(String shopContact) {
		this.shopContact = shopContact;
	}
	
	
	
	
	
	

}
