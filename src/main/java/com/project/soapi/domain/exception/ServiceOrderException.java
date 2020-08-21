package com.project.soapi.domain.exception;

public class ServiceOrderException extends RuntimeException {

	static final long serialVersionUID = 1L;
	
	public ServiceOrderException(String message) {
		super(message);
	}

}
