package com.jsp.capstoneProject.exception;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.jsp.capstoneProject.util.SuccessResponse;

import jakarta.mail.MessagingException;

@RestControllerAdvice
public class CapstoneExceptionHandler 
{
	@ExceptionHandler(SQLIntegrityConstraintViolationException.class)
	public ResponseEntity<SuccessResponse> sqlICVE(SQLIntegrityConstraintViolationException e)
	{
		SuccessResponse data=SuccessResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.dateTime(LocalDateTime.now())
				.msg("you cant perform this operation")
				.data(e.getMessage()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(NotFoundException.class)
	public ResponseEntity<SuccessResponse> notFound(NotFoundException e)
	{
		SuccessResponse data=SuccessResponse.builder().status(HttpStatus.NOT_FOUND.value())
				.dateTime(LocalDateTime.now())
				.msg("you cant perform this operation")
				.data(e.getMsg()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(MessagingException.class)
	public ResponseEntity<SuccessResponse> msgExc(MessagingException e)
	{
		SuccessResponse data=SuccessResponse.builder().status(HttpStatus.BAD_REQUEST.value())
				.dateTime(LocalDateTime.now())
				.msg("you cant perform this operation")
				.data(e.getMessage()).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.BAD_REQUEST);
	}
}
