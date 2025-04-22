package com.jsp.capstoneProject.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.capstoneProject.DAO.DrugDAO;
import com.jsp.capstoneProject.entity.Admin;
import com.jsp.capstoneProject.entity.Drug;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.util.SuccessResponse;

@Service
public class DrugService 
{
	@Autowired
	DrugDAO drugDAO;
	
	public ResponseEntity<SuccessResponse> save(Drug drug) 
	{
		 Drug db= drugDAO.saveDrug(drug);
		 SuccessResponse data=SuccessResponse.builder().status(HttpStatus.ACCEPTED.value())
				 .msg("Drug Saved Successfully")
				 .dateTime(LocalDateTime.now())
				 .data(db).build();
		 return new ResponseEntity<SuccessResponse>(data,HttpStatus.ACCEPTED);
	}

	public ResponseEntity<SuccessResponse> deleteDrug(int id) 
	{
		 Drug db= drugDAO.deleteDrug(id);
		 if(db!=null) {
		 SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
				 .msg("Drug deleted Successfully")
				 .dateTime(LocalDateTime.now())
				 .data(db).build();
		 return new ResponseEntity<SuccessResponse>(data,HttpStatus.OK);
		 }
		 else
		 {
			  throw new NotFoundException("Drug "+id+" Not Found");
		 }
	}

	public ResponseEntity<SuccessResponse> fetchDrugByname(String name) 
	{
		 Drug db= drugDAO.fetchByName(name);
		 if(db!=null) {
		 SuccessResponse data=SuccessResponse.builder().status(HttpStatus.FOUND.value())
				 .msg("Drug deleted Successfully")
				 .dateTime(LocalDateTime.now())
				 .data(db).build();
		 return new ResponseEntity<SuccessResponse>(data,HttpStatus.FOUND);
		 }
		 else
		 {
			  throw new NotFoundException("Drug "+name+" Not Found");
		 }
	}

	public ResponseEntity<SuccessResponse> fetchAll() 
	{
		
		 List<Drug> db= drugDAO.fetchAllDrugs();
		 if(db!=null) {
		 SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
				 .msg("Drug deleted Successfully")
				 .dateTime(LocalDateTime.now())
				 .data(db).build();
		 return new ResponseEntity<SuccessResponse>(data,HttpStatus.OK);
		 }
		 else
		 {
			  throw new NotFoundException("Cant get the drugs");
		 }
	}

	public ResponseEntity<SuccessResponse> updateDrug(Drug drug) 
	{
		Drug db= drugDAO.updateDrug(drug);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.CREATED.value())
					.msg("Drug data updated successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);
		}
		throw new NotFoundException("Drug not found");
	}
	

}
