package com.project.soapi.api.exceptionhandler;

import java.time.OffsetDateTime;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.project.soapi.domain.exception.EntityNotFoundException;
import com.project.soapi.domain.exception.ServiceOrderException;

@ControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
	
	@Autowired
	private MessageSource messageSource;
	
	@ExceptionHandler(ServiceOrderException.class)
	public ResponseEntity<Object> ServiceOrderHandler(ServiceOrderException ex, WebRequest request) {
		var status = HttpStatus.BAD_REQUEST;
		
		var issue = new Issue();
		issue.setStatus(status.value());
		issue.setTitle(ex.getMessage());
		issue.setDateTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
		
	}
	
	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<Object> handleEntityNotFound(ServiceOrderException ex, WebRequest request) {
		var status = HttpStatus.NOT_FOUND;
		
		var issue = new Issue();
		issue.setStatus(status.value());
		issue.setTitle(ex.getMessage());
		issue.setDateTime(OffsetDateTime.now());
		
		return handleExceptionInternal(ex, issue, new HttpHeaders(), status, request);
		
	}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		var fields = new ArrayList<Issue.Field>();
		
		for (ObjectError error : ex.getBindingResult().getAllErrors()) {
			String name = ((FieldError) error).getField();
			String message = messageSource.getMessage(error, LocaleContextHolder.getLocale());
			
			fields.add(new Issue.Field(name, message));
		}
		
		var issue = new Issue();
		issue.setStatus(status.value());
		issue.setTitle("One or more fields are invalid, please try again.");
		issue.setDateTime(OffsetDateTime.now());
		issue.setFields(fields);
		
		return super.handleExceptionInternal(ex, issue, headers, status, request);
	}
	
}
