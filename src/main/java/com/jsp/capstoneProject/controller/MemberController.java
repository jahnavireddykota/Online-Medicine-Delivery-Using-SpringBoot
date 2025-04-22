package com.jsp.capstoneProject.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.service.MemberService;
import com.jsp.capstoneProject.util.SuccessResponse;

import jakarta.mail.MessagingException;


@CrossOrigin(origins = "http://127.0.0.1:5500")
@RestController
@RequestMapping("/member")
public class MemberController 
{
	@Autowired
	MemberService memberService;
	
	@PutMapping("/uploadprofile")
	public ResponseEntity<SuccessResponse> uploadProfile(@RequestParam int memid,@RequestParam MultipartFile file) throws IOException
	{
//		System.out.println(file.getBytes());
//		System.out.println(file.getOriginalFilename());
		return memberService.uploadprofile(memid,file);
	}
	
	@GetMapping("/fetchprofile")
	public ResponseEntity<byte[]> fetchProfile(@RequestParam int memid)
	{
		return memberService.fetchProfile(memid);
	}
	
	@PostMapping("/register")
	public ResponseEntity<SuccessResponse> save(@RequestBody Member member) throws MessagingException
	{
		return memberService.save(member);
	}
	
	@PutMapping("/update")
	public ResponseEntity<SuccessResponse> update(@RequestBody Member member)
	{
		return memberService.updateMember(member);
	}
	
	@GetMapping("/fetch")
	public ResponseEntity<SuccessResponse> fetchById(@RequestParam int id)
	{
		return memberService.fetchById(id);
	}
	
	@GetMapping("/fetchByEmail")
	public ResponseEntity<SuccessResponse> fetchByEmail(@RequestParam String email)
	{
		return memberService.fetchByEmail(email);
	}
	@DeleteMapping("/delete")
	public ResponseEntity<SuccessResponse> deleteMember(@RequestParam int id)
	{
		return memberService.delete(id);
	}
	
	@GetMapping("/fetchall")
	public ResponseEntity<SuccessResponse> fetchAll()
	{
		return memberService.fetchAllMember();
	}
}
