package com.app.excelcsvreadwriteapp.entity;

public class User {
	
	private String firstName;
	private String lastName;
	private String address;
	private String email;
	
	
	public User() {
		super();
	}
	public User(String firstName, String lastName, String address, String email) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.address = address;
		this.email = email;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	@Override
	public String toString() {
		return "User [firstName=" + firstName + ", lastName=" + lastName + ", address=" + address + ", email=" + email
				+ "]";
	}
	
	

}
