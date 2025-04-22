package com.jsp.capstoneProject.controller;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.capstoneProject.service.OrderService;
import com.jsp.capstoneProject.util.SuccessResponse;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/orders")
public class OrderController 
{
	@Autowired
	OrderService orderService;
	
	@PostMapping("/placeorder")
	public ResponseEntity<SuccessResponse> placeOrder(@RequestParam int memid,@RequestParam List<String> drugname)
	{
		return orderService.orderDetails(memid, drugname);
	}
}
