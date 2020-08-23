package com.project.soapi.domain.model;

import java.math.BigDecimal;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.project.soapi.domain.exception.ServiceOrderException;

@Entity
public class ServiceOrder {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne
	private Customer customer;
	
	private String description;
	private BigDecimal value;
	
	@Enumerated(EnumType.STRING)
	private ServiceOrderStatus status;
	
	private OffsetDateTime openingDate;
	private OffsetDateTime closingDate;
	
	@OneToMany(mappedBy = "serviceOrder")
	private List<Comment> comments = new ArrayList<>();
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Customer getCustomer() {
		return customer;
	}
	
	public void setCustomer(Customer customer) {
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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ServiceOrder other = (ServiceOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	public boolean canBeClosed() {
		return getStatus().equals(ServiceOrderStatus.OPENED);
	}
	
	public boolean canNotBeClosed() {
		return !getStatus().equals(ServiceOrderStatus.OPENED);
	}

	public void close() {
		if(canNotBeClosed()) {
			throw new ServiceOrderException("Service order cannot be closed");
		}
		
		setStatus(ServiceOrderStatus.CLOSED);
		setClosingDate(OffsetDateTime.now());
	}
	
}
