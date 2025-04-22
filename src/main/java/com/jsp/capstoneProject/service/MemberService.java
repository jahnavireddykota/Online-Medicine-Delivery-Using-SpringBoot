package com.jsp.capstoneProject.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.jsp.capstoneProject.DAO.MemberDAO;
import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.util.CapstoneMailSender;
import com.jsp.capstoneProject.util.SuccessResponse;

import jakarta.mail.MessagingException;

@Service
public class MemberService 
{
	@Autowired
	MemberDAO memberDAO;
	
	@Autowired
	CapstoneMailSender sender;
	
	
	public ResponseEntity<SuccessResponse> save(Member member) throws MessagingException
	{
		Member db= memberDAO.saveMember(member);
		if(db!=null)
		{
			sender.requestEnable(member);
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.CREATED.value())
					.msg("Data Saved successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);
		}
		else
		{
			throw new NotFoundException("Error in saving data");
		}
		
		
	}

	public ResponseEntity<SuccessResponse> updateMember(Member member) 
	{
		Member db= memberDAO.updateMember(member);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Data updated successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Member Not Found");
		}
	}

	public ResponseEntity<SuccessResponse> fetchById(int id) 
	{
		Member db= memberDAO.fetchById(id);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.FOUND.value())
					.msg("Data Found successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Member "+id+ "not found");
		}
	}

	public ResponseEntity<SuccessResponse> delete(int id) 
	{
		Member db= memberDAO.deleteMember(id);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Data deleted successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Member "+id +"not found");
		}
	}

	public ResponseEntity<SuccessResponse> fetchAllMember() 
	{
		List<Member> dbList= memberDAO.fetchAll();
		if(dbList!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.FOUND.value())
					.msg("Found All successfully")
					.dateTime(LocalDateTime.now())
					.data(dbList).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Data not  found");
		}
		
	}

	public ResponseEntity<SuccessResponse> uploadprofile(int memid, MultipartFile file) throws IOException 
	{
		Member db= memberDAO.fetchById(memid);
		if(db!=null)
		{
			db.setProfile(file.getBytes());
			memberDAO.updateMember(db);
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Profile updated successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
			
		}
		else
		{
			throw new NotFoundException("Member not found");
		}
	}
	
	public ResponseEntity<byte[]> fetchProfile(int memid)
	{
		Member db= memberDAO.fetchById(memid);
		if(db!=null)
		{
			byte[] data=db.getProfile();
			HttpHeaders header=new HttpHeaders();
			header.setContentType(MediaType.IMAGE_JPEG);
			return new ResponseEntity<byte[]>(data,header,HttpStatus.FOUND);
		}
		else
		{
			throw new NotFoundException("Member "+db.getId()+ "not Found");
		}
	}

	public ResponseEntity<SuccessResponse> fetchByEmail(String email) 
	{
		Member db= memberDAO.fetchMemEmail(email);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Data found successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Member "+email+" not found");
		}
	}
}
