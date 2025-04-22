package com.jsp.capstoneProject.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.capstoneProject.entity.Payment;
import com.jsp.capstoneProject.repo.PaymentRepo;

@Repository
public class PaymentDAO 
{
	@Autowired
	PaymentRepo paymentRepo;
	
	public Payment savePayment(Payment payment) 
	{
		return paymentRepo.save(payment);
	}
}
