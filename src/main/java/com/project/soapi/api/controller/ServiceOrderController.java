package com.project.soapi.api.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.soapi.api.model.ServiceOrderDTO;
import com.project.soapi.api.model.ServiceOrderInputDTO;
import com.project.soapi.domain.model.ServiceOrder;
import com.project.soapi.domain.repository.ServiceOrderRepository;
import com.project.soapi.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service_orders")
public class ServiceOrderController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServiceOrderDTO create(@Valid @RequestBody ServiceOrderInputDTO serviceOrderInput) {
		ServiceOrder serviceOrder = DTOtoServiceOrder(serviceOrderInput);
		
		return serviceOrderToDTO(serviceOrderService.create(serviceOrder));
	}
	
	@GetMapping
	public List<ServiceOrderDTO> list() {
		return serviceOrderToDTOCollection(serviceOrderRepository.findAll());
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ServiceOrderDTO> search(@PathVariable Long id) {
		Optional<ServiceOrder> serviceOrder = serviceOrderRepository.findById(id);
		
		if (serviceOrder.isPresent()) {
			ServiceOrderDTO dto = serviceOrderToDTO(serviceOrder.get());
			
			return ResponseEntity.ok(dto);
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{id}/closing")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void close(@PathVariable Long id) {
		serviceOrderService.close(id);
	}
	
	private ServiceOrderDTO serviceOrderToDTO(ServiceOrder serviceOrder) {
		return modelMapper.map(serviceOrder, ServiceOrderDTO.class);
	}
	
	private List<ServiceOrderDTO> serviceOrderToDTOCollection(List<ServiceOrder> serviceOrders) {
		return serviceOrders.stream().map(serviceOrder -> serviceOrderToDTO(serviceOrder)).collect(Collectors.toList());
	}
	
	private ServiceOrder DTOtoServiceOrder(ServiceOrderInputDTO serviceOrderInput) {
		return modelMapper.map(serviceOrderInput, ServiceOrder.class);
	}

}
