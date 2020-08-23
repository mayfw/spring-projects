package com.project.soapi.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.soapi.domain.model.Customer;
import com.project.soapi.domain.repository.CustomerRepository;
import com.project.soapi.domain.service.CustomerService;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping
	public List<Customer> list() {
		return customerRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Customer> search(@PathVariable Long id) {
		Optional<Customer> customer = customerRepository.findById(id);
		
		if (customer.isPresent()) {
			return ResponseEntity.ok(customer.get());
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Customer create(@Valid @RequestBody Customer customer) {
		return customerService.create(customer);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Customer> update(@Valid @PathVariable Long id, @RequestBody Customer customer) {
		
		if (!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		customer.setId(id);
		customer = customerService.create(customer);
		
		return ResponseEntity.ok(customer);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		
		if (!customerRepository.existsById(id)) {
			return ResponseEntity.notFound().build();
		}
		
		customerService.delete(id);
		
		return ResponseEntity.noContent().build();
	}

}
