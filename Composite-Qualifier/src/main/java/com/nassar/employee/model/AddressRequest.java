package com.nassar.employee.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonProperty;

@Component
public class AddressRequest {
	@JsonProperty("employee_id")
	private Long employee_id;
	@JsonProperty("city")
	private String city;
	@JsonProperty("streetName")
	private String streetName;
	public Long getEmployee_id() {
		return employee_id;
	}
	
	private Long address_id;
	
	public void setEmployee_id(Long employee_id) {
		this.employee_id = employee_id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getStreetName() {
		return streetName;
	}
	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}
	
	public AddressRequest(Long address_id, Long employee_id, String city, String streetName) {
		this.address_id= address_id;
		this.employee_id = employee_id;
		this.city = city;
		this.streetName = streetName;
	}
	public Long getAddress_id() {
		return address_id;
	}
	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
	}
	public AddressRequest () {};
}
