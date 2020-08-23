package com.project.soapi.api.model;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

public class ServiceOrderInputDTO {
	
	@NotBlank
	private String description;
	
	@NotNull
	private BigDecimal value;
	
	@Valid
	@NotNull
	private CustomerIdInputDTO customer;
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public BigDecimal getValue() {
		return value;
	}
	
	public void setValue(BigDecimal value) {
		this.value = value;
	}
	
	public CustomerIdInputDTO getCustomer() {
		return customer;
	}
	
	public void setCustomer(CustomerIdInputDTO customer) {
		this.customer = customer;
	}
	
}
