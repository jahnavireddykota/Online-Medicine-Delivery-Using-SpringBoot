package com.jsp.capstoneProject.DAO;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.capstoneProject.entity.Admin;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.repo.AdminRepo;

@Repository
public class AdminDAO 
{
	@Autowired
	AdminRepo adminRepo;
	
	public Admin saveAdmin(Admin admin)
	{
		return adminRepo.save(admin);
	}
	
	public Admin updateAdmin(Admin admin)
	{
		Optional<Admin> op= adminRepo.findById(admin.getAdminid());
		if(op.isPresent())
		{
			Admin db=op.get();
			if(admin.getAdminemailid()==null)
			{
				admin.setAdminemailid(db.getAdminemailid());
			}
			if(admin.getAdminname()==null)
			{
				admin.setAdminname(db.getAdminname());
			}
			if(admin.getGender()==null)
			{
				admin.setGender(db.getGender());
			}
			if(admin.getPwd()==null)
			{
				admin.setPwd(db.getPwd());
			}
			if(admin.getMobile()==0)
			{
				admin.setMobile(db.getMobile());
			}
			return adminRepo.save(admin);
		}
		else
		{
			return null;
		}
		
	}
	
	public  Admin deleteAdmin(int id)
	{
		Optional<Admin> op= adminRepo.findById(id);
		if(op.isPresent())
		{
			Admin admin=op.get();
			adminRepo.delete(admin);
			return admin;
		}
		return null;
	}
	
	public Admin fetchAdminById(int id)
	{
		Optional<Admin> op= adminRepo.findById(id);
		if(op.isPresent())
		{
			return op.get();
		}
		return null;
	}
	
	public Admin login(Admin admin)
	{
		Admin db=adminRepo.findByAdminemailid(admin.getAdminemailid());
		if(db!=null)
		{
			if(admin.getPwd().equalsIgnoreCase(db.getPwd()))
			{
				return db;
			}
			else
			{
				throw new NotFoundException("Password Mismatch");
			}
		}
		else
		{
			throw new NotFoundException("Admin "+admin.getAdminemailid()+" Not found");
		}
	}
	
	public Admin adminlogin(String emailid,String password)
	{
		Admin db= adminRepo.findByAdminemailid(emailid);
		if(db!=null)
		{
			if(db.getPwd().equalsIgnoreCase(password))
			{
				return db;
			}
			else
			{
				throw new NotFoundException("Password Mismatch");
			}
		}
		else
		{
			throw new NotFoundException("Admin "+emailid+" Not found");
		}
	}

	public Admin fetchAdminByEmail(String adminemailid) 
	{
		Admin db= adminRepo.findByAdminemailid(adminemailid);
		if(db!=null)
		{
			return db;
		}
		else
		{
			throw new NotFoundException("Email not found");
		}
	}

}
