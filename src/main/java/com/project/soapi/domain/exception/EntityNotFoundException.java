package com.project.soapi.domain.exception;

public class EntityNotFoundException extends ServiceOrderException {

	private static final long serialVersionUID = 1L;

	public EntityNotFoundException(String message) {
		super(message);
	}
	
}
