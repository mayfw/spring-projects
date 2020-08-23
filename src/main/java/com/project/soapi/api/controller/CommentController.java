package com.project.soapi.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.project.soapi.api.model.CommentDTO;
import com.project.soapi.api.model.CommentInputDTO;
import com.project.soapi.domain.exception.EntityNotFoundException;
import com.project.soapi.domain.model.Comment;
import com.project.soapi.domain.model.ServiceOrder;
import com.project.soapi.domain.repository.ServiceOrderRepository;
import com.project.soapi.domain.service.ServiceOrderService;

@RestController
@RequestMapping("/service_orders/{serviceOrderId}/comments")
public class CommentController {
	
	@Autowired
	private ServiceOrderService serviceOrderService;
	
	@Autowired
	private ServiceOrderRepository serviceOrderRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@GetMapping
	public List<CommentDTO> list(@PathVariable Long serviceOrderId) {
		ServiceOrder serviceOrder = serviceOrderRepository.findById(serviceOrderId)
				.orElseThrow(() -> new EntityNotFoundException("Service order not found"));
		
		return commentToDTOCollection(serviceOrder.getComments());
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public CommentDTO create(@PathVariable Long serviceOrderId, @Valid @RequestBody CommentInputDTO commentInput) {
		Comment comment = serviceOrderService.createComment(serviceOrderId, commentInput.getDescription());
		
		return commentToDTO(comment);
	}
	
	private CommentDTO commentToDTO(Comment comment) {
		return modelMapper.map(comment, CommentDTO.class);
	}
	
	private List<CommentDTO> commentToDTOCollection(List<Comment> comments) {
		return comments.stream().map(comment -> commentToDTO(comment)).collect(Collectors.toList());
	}

}
