package com.project.soapi.domain.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.soapi.domain.exception.EntityNotFoundException;
import com.project.soapi.domain.exception.ServiceOrderException;
import com.project.soapi.domain.model.Comment;
import com.project.soapi.domain.model.Customer;
import com.project.soapi.domain.model.ServiceOrder;
import com.project.soapi.domain.model.ServiceOrderStatus;
import com.project.soapi.domain.repository.CommentRepository;
import com.project.soapi.domain.repository.CustomerRepository;
import com.project.soapi.domain.repository.ServiceOrderRepository;

@Service
public class ServiceOrderService {
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CommentRepository commentRepository;
	
	public ServiceOrder create(ServiceOrder serviceOrder) {
		Customer customer = customerRepository.findById(serviceOrder.getCustomer().getId())
				.orElseThrow(() -> new ServiceOrderException("Customer not found"));
		
		serviceOrder.setCustomer(customer);
		serviceOrder.setStatus(ServiceOrderStatus.OPENED);
		serviceOrder.setOpeningDate(OffsetDateTime.now());
		
		return serviceOrderRepository.save(serviceOrder);
	
	}
	
	public void close(Long serviceOrderId) {
		ServiceOrder serviceOrder = searchServiceOrder(serviceOrderId);
		
		serviceOrder.close();
		
		serviceOrderRepository.save(serviceOrder);
	}
	
	public Comment createComment(Long serviceOrderId, String description) {
		ServiceOrder serviceOrder = searchServiceOrder(serviceOrderId);
		
		Comment comment = new Comment();
		comment.setDate(OffsetDateTime.now());
		comment.setDescription(description);
		comment.setServiceOrder(serviceOrder);
		
		return commentRepository.save(comment);
	}

	private ServiceOrder searchServiceOrder(Long serviceOrderId) {
		return serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Service order not found"));
	}

}
