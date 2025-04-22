package com.jsp.capstoneProject.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.jsp.capstoneProject.entity.Admin;
import com.jsp.capstoneProject.service.AdminService;
import com.jsp.capstoneProject.util.CapstoneMailSender;
import com.jsp.capstoneProject.util.SuccessResponse;

@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/admin")
public class AdminController 
{
	@Autowired
	AdminService adminService;
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> saveAdmin(@RequestBody Admin admin)
	{
		return adminService.saveAdmin(admin);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> updateAdmin(@RequestBody Admin admin)
	{
		return adminService.updateAdmin(admin);
	}
	
	//http://localhost:1010/admin/getbyid?id=?
	@GetMapping("/getbyid")
	public ResponseEntity<SuccessResponse> fetchById(@RequestParam int id)
	{
		return adminService.fetchAdmin(id);
	}
	
	@GetMapping("/getbyemail")
	public ResponseEntity<SuccessResponse> fetchByEmail(@RequestParam String adminemailid)
	{
		return adminService.fetchByEmail(adminemailid);
	}
//	@GetMapping("/login1")
//	public ResponseEntity<SuccessResponse> adminLogin(@RequestBody Admin admin)
//	{
//		return adminService.adminLogin(admin);
//	}
	
	@GetMapping("/login")
	public ResponseEntity<SuccessResponse> adminLogin1(@RequestParam String adminemailid,@RequestParam String pwd)
	{
		return adminService.adminLogin(adminemailid, pwd);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteAdmin(@RequestParam int id)
	{
		return adminService.deleteAdmin(id);
	}
	
	@GetMapping("/enable")
	public ResponseEntity<SuccessResponse> enableMember(@RequestParam() int adminid,@RequestParam int memberid)
	{
		return adminService.enableMember(adminid,memberid);
	}
}
