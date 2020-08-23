package com.project.soapi.api.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;

import com.project.soapi.domain.model.ServiceOrderStatus;

public class ServiceOrderDTO {
	
	private Long id;
	private CustomerSummaryDTO customer;
	private String description;
	private BigDecimal value;
	private ServiceOrderStatus status;
	private OffsetDateTime openingDate;
	private OffsetDateTime closingDate;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public CustomerSummaryDTO getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerSummaryDTO customer) {
		this.customer = customer;
	}

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
	
	public ServiceOrderStatus getStatus() {
		return status;
	}
	
	public void setStatus(ServiceOrderStatus status) {
		this.status = status;
	}
	
	public OffsetDateTime getOpeningDate() {
		return openingDate;
	}
	
	public void setOpeningDate(OffsetDateTime openingDate) {
		this.openingDate = openingDate;
	}
	
	public OffsetDateTime getClosingDate() {
		return closingDate;
	}
	
	public void setClosingDate(OffsetDateTime closingDate) {
		this.closingDate = closingDate;
	}
	
}
