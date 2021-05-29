package com.online.flowers.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class CustomerModel {
	@Id
	@Email
	private String email;
	
	@NotEmpty
	@Pattern(regexp = "(MR|MRS|MS)")
	private String title;
	
	@NotEmpty
	private String name;
	
	@NotEmpty
	@Size(min = 5, max = 45)
	private String address;
	
	@NotEmpty
	@Size(min = 6, max = 18)
	private String password;
	
	@NotEmpty
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private String dob;
	
	

	public CustomerModel() {
		super();
	}

	public CustomerModel(@Email String email, @NotEmpty @Max(4) @Pattern(regexp = "(MR.|MRS.|MS.)$") String title,
			@NotEmpty String name, @NotEmpty @Size(min = 5, max = 45) String address,
			@NotEmpty @Size(min = 6, max = 18) String password, @NotEmpty String dob) {
		super();
		this.email = email;
		this.title = title;
		this.name = name;
		this.address = address;
		this.password = password;
		this.dob = dob;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDob() {
		return dob;
	}

	public void setDob(String dob) {
		this.dob = dob;
	}
	
	
	
	
	

}
