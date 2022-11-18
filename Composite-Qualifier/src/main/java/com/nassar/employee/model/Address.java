package com.nassar.employee.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Entity
@Table(name = "Address")
@Data
public class Address {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long address_id;
	
	@JsonProperty("city")
	private String city;
	@JsonProperty("streetName")
	private String streetName;
	
	@ManyToOne
	@JoinColumn(name = "employee_id")
	@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
	Employee employee;
	
	public Address (Long address_id, String city, String streetName, Employee employee) {
		this.address_id = address_id;
		this.city = city;
		this.streetName = streetName;
		this.employee = employee;
	}
	
	public Address (String city, String streetName, Employee employee) {
		this.city = city;
		this.streetName = streetName;
		this.employee = employee;
	}
	
	public Address () {
		
	}


	public Long getAddress_id() {
		return address_id;
	}

	public void setAddress_id(Long address_id) {
		this.address_id = address_id;
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

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

}
