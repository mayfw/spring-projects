package com.project.soapi.api.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.soapi.domain.model.Customer;

@RestController
public class CustomerController {
	
	@GetMapping("/customers")
	public List<Customer> listAll() {
		var customer1 = new Customer();
		customer1.setId(1L);
		customer1.setName("May Wong");
		customer1.setEmail("maii2904@gmail.com");
		customer1.setPhone("33333333");
		
		var customer2 = new Customer();
		customer2.setId(2L);
		customer2.setName("Diego Santos");
		customer2.setEmail("bonodns@gmail.com");
		customer2.setPhone("5555555");
		
		return Arrays.asList(customer1, customer2);
	}

}
