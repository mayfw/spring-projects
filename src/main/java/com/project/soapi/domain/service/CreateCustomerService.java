package com.project.soapi.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.soapi.domain.exception.ServiceOrderException;
import com.project.soapi.domain.model.Customer;
import com.project.soapi.domain.repository.CustomerRepository;

@Service
public class CreateCustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public Customer create(Customer customer) {
		Customer existentCustomer = customerRepository.findByEmail(customer.getEmail());
		
		if (existentCustomer != null && !existentCustomer.equals(customer)) {
			throw new ServiceOrderException("Email already exists");
		}
		
		return customerRepository.save(customer);
	}
	
	public void delete(Long id) {
		customerRepository.deleteById(id);
	}
	
}
