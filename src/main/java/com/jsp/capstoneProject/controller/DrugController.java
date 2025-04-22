package com.jsp.capstoneProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.capstoneProject.entity.Drug;
import com.jsp.capstoneProject.service.DrugService;
import com.jsp.capstoneProject.util.SuccessResponse;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RequestMapping("/drug")
@RestController
public class DrugController 
{
	@Autowired
	DrugService drugService;
	
	@PostMapping("/save")
	public ResponseEntity<SuccessResponse> saveDrug(@RequestBody Drug drug)
	{
		return drugService.save(drug);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteDrug(@RequestParam int id)
	{
		return drugService.deleteDrug(id);
	}
	
	@GetMapping("/fetchbyname")
	public ResponseEntity<SuccessResponse> fetchByname(@RequestParam String name)
	{
		return drugService.fetchDrugByname(name);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateDrug(@RequestBody Drug drug)
	{
		return drugService.updateDrug(drug);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<SuccessResponse> fetchAllDrugs()
	{
		return drugService.fetchAll();
	}
}
