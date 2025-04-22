package com.jsp.capstoneProject.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.jsp.capstoneProject.DAO.AdminDAO;
import com.jsp.capstoneProject.DAO.MemberDAO;
import com.jsp.capstoneProject.entity.Admin;
import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.util.SuccessResponse;

@Service
public class AdminService 
{
	@Autowired
	AdminDAO adminDAO;
	
	@Autowired
	MemberDAO memberDAO;
	
	public ResponseEntity<SuccessResponse> saveAdmin(Admin admin)
	{
		SuccessResponse data= SuccessResponse.builder().msg("Admin saved successfully")
				.status(HttpStatus.CREATED.value())
				.data(adminDAO.saveAdmin(admin))
				.dateTime(LocalDateTime.now()).build();
		return new ResponseEntity<SuccessResponse>(data,HttpStatus.CREATED);
	}
	
	public ResponseEntity<SuccessResponse> updateAdmin(Admin admin)
	{
		Admin db= adminDAO.updateAdmin(admin);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.CREATED.value())
					.msg("Admin data updated successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);
		}
		throw new NotFoundException("Admin not found");
		
	}
	
	public ResponseEntity<SuccessResponse> deleteAdmin(int id)
	{
		Admin db= adminDAO.deleteAdmin(id);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Admin data deleted successfully")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			throw new NotFoundException("Admin"+ id + "not found");
		}
	}
	
	public ResponseEntity<SuccessResponse> fetchAdmin(int id)
	{
		Admin db=adminDAO.fetchAdminById(id);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Admin data found")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			 throw new NotFoundException("Admin id"+ id +"not found");
		}
	}
	
	
	public ResponseEntity<SuccessResponse> adminLogin(String email,String password)
	{
		SuccessResponse data=SuccessResponse.builder().status(HttpStatus.FOUND.value())
				.msg("Admin Login successfully")
				.dateTime(LocalDateTime.now())
				.data(adminDAO.adminlogin(email, password)).build();
		return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
	}

	public ResponseEntity<SuccessResponse> enableMember(int adminid, int memberid) 
	{
		Admin db= adminDAO.fetchAdminById(adminid);
		if(db!=null)
		{
			Member memberdb= memberDAO.fetchById(memberid);
			if(memberdb!=null)
			{
				memberdb.setDisabled(true);
				memberDAO.updateMember(memberdb);
				SuccessResponse data=SuccessResponse.builder().status(HttpStatus.CREATED.value())
						.msg("Member Enabled successfully")
						.dateTime(LocalDateTime.now())
						.data(memberdb).build();
				return new ResponseEntity<SuccessResponse>(data, HttpStatus.CREATED);
			}
			else
			{
				throw new NotFoundException("Member id "+memberid+" not found");
			}
		}
		else
		{
			throw new NotFoundException("Admin id "+adminid+" not found");
		}
	}

	public ResponseEntity<SuccessResponse> fetchByEmail(String adminemailid) 
	{
		Admin db=adminDAO.fetchAdminByEmail(adminemailid);
		if(db!=null)
		{
			SuccessResponse data=SuccessResponse.builder().status(HttpStatus.OK.value())
					.msg("Admin data found")
					.dateTime(LocalDateTime.now())
					.data(db).build();
			return new ResponseEntity<SuccessResponse>(data, HttpStatus.OK);
		}
		else
		{
			 throw new NotFoundException("Admin "+ adminemailid +"not found");
		}
	}
	
	
//	public ResponseEntity<SuccessResponse> adminLogin1(Admin admin)
//	{
//		SuccessResponse data=SuccessResponse.builder().status(HttpStatus.FOUND.value())
//				.msg("Admin Login successfully")
//				.dateTime(LocalDateTime.now())
//				.data(adminDAO.login(admin)).build();
//		return new ResponseEntity<SuccessResponse>(data, HttpStatus.FOUND);
//	}
}

