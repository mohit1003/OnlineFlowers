package com.online.flowers.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
public class CustomerModel {
	@Id
	@Email
	private String email;

	@NotEmpty(message = "Title cannot be empty")
	@Pattern(regexp = "(MR|MRS|MS)", message = "Invalid title")
	private String title;

	@NotEmpty(message = "FirstName cannot be empty")
	@Size(min = 2, max = 30, message = "Name count must be between 5 to 30")
	private String firstName;

	@NotEmpty(message = "LastName cannot be empty")
	@Size(min = 2, max = 30, message = "Name count must be between 5 to 30")
	private String lastName;

	@NotEmpty(message = "Address cannot be empty")
	@Size(min = 5, max = 45, message = "Address count must be between 5 to 45")
	private String address;

	@NotEmpty(message = "City cannot be empty")
	@Size(max = 30, message = "City cannot exceed 30")
	private String city;

	@NotEmpty(message = "Country cannot be empty")
	@Size(max = 30, message = "Country cannot exceed 30")
	private String country;

	@NotEmpty(message = "Password cannot be empty")
	@Column(columnDefinition = "LONGTEXT")
	private String password;

	@NotEmpty(message = "contact cannot be empty")
	@Size(min = 10, max = 10)
	private String contact;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = @JoinColumn(name = "email"), inverseJoinColumns = @JoinColumn(name = "role_id"))
	private List<Roles> roles;

	public CustomerModel() {
		super();
	}

	public CustomerModel(@Email String email,
			@NotEmpty(message = "Title cannot be empty") @Pattern(regexp = "(MR|MRS|MS)", message = "Invalid title") String title,
			@NotEmpty(message = "FirstName cannot be empty") @Size(min = 5, max = 30, message = "Name count must be between 5 to 30") String firstName,
			@NotEmpty(message = "LastName cannot be empty") @Size(min = 5, max = 30, message = "Name count must be between 5 to 30") String lastName,
			@NotEmpty(message = "Address cannot be empty") @Size(min = 5, max = 45, message = "Address count must be between 5 to 45") String address,
			@NotEmpty(message = "City cannot be empty") @Size(max = 30, message = "City cannot exceed 30") String city,
			@NotEmpty(message = "Country cannot be empty") @Size(max = 30, message = "Country cannot exceed 30") String country,
			@NotEmpty(message = "Password cannot be empty") String password,
			@NotEmpty(message = "contact cannot be empty") @Size(min = 10, max = 10) String contact) {
		super();
		this.email = email;
		this.title = title;
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.city = city;
		this.country = country;
		this.password = password;
		this.contact = contact;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getContact() {
		return contact;
	}

	public void setContact(String contact) {
		this.contact = contact;
	}

	public List<Roles> getRoles() {
		return roles;
	}

	public void setRoles(List<Roles> roles) {
		this.roles = roles;
	}

}
