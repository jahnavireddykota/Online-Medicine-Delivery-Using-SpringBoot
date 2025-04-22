package com.jsp.capstoneProject.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.capstoneProject.DAO.DrugDAO;
import com.jsp.capstoneProject.DAO.MemberDAO;
import com.jsp.capstoneProject.DAO.PaymentDAO;
import com.jsp.capstoneProject.entity.Drug;
import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.entity.Payment;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.util.CapstoneMailSender;
import com.jsp.capstoneProject.util.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class OrderService 
{
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	DrugDAO drugDAO;
	
	@Autowired
	Payment payment;
	
	@Autowired
	PaymentDAO paymentDAO;
	
	@Autowired
	CapstoneMailSender sender;
	
	public ResponseEntity<SuccessResponse> orderDetails(int memid,List<String> drugname)
	{
		Member member= memberDAO.fetchById(memid);
		List<Drug> druglist=new ArrayList<Drug>();
		double totalamount=0;
		if(member!=null)
		{
			for(String drug:drugname)
			{
				Drug drugdb= drugDAO.fetchByName(drug);
				if(drugdb!=null)
				{
					if(drugdb.getQuality()!=0)
					{
						totalamount+=drugdb.getPrice();
						druglist.add(drugdb);
					}
					else
					{
						throw new NotFoundException("Drug out of stock");
					}
				}
				else
				{
					throw new NotFoundException("drug not found");
				}
			}
			payment.setMemberid(memid);
			payment.setOrderAmount(totalamount);
			payment.setDrug(druglist);
			paymentDAO.savePayment(payment);
			try {
				sender.sendOrderDetails(payment);
			} catch (MessagingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Order placed")
					.dateTime(LocalDateTime.now())
					.data(member).build();
			return new ResponseEntity<SuccessResponse>(data,HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Member not found");
		}
		
	}
}
