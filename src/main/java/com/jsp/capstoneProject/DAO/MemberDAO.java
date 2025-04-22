package com.jsp.capstoneProject.DAO;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jsp.capstoneProject.entity.Member;
import com.jsp.capstoneProject.exception.NotFoundException;
import com.jsp.capstoneProject.repo.MemberRepo;

@Repository
public class MemberDAO 
{
	@Autowired
	MemberRepo memberRepo;
	
	public Member saveMember(Member member)
	{
		return memberRepo.save(member);
	}
	
	public Member updateMember(Member member)
	{
		Optional<Member> op= memberRepo.findById(member.getId());
		if(op.isPresent())
		{
			Member db= op.get();
			
			if(member.getEmail()==null)
			{
				member.setEmail(db.getEmail());
			}
			if(member.getName()==null)
			{
				member.setName(db.getName());
			}
			if(member.getGender()==null)
			{
				member.setGender(db.getGender());
			}
			if(member.getAddress()==null)
			{
				member.setAddress(db.getAddress());
			}
			if(member.getDob()==null)
			{
				member.setDob(db.getDob());
			}
			if(member.getMoblie()==0)
			{
				member.setMoblie(db.getMoblie());
			}
			if(member.getAddress()==null)
			{
				member.setAddress(db.getAddress());
			}
			return memberRepo.save(member);
		}
		else
		{
			return null;
		}
	}
	
	public Member fetchById(int id)
	{
		Optional<Member>op= memberRepo.findById(id);
		if(op.isPresent())
		{
			Member db= op.get();
			return db;
		}
		return null;
	}
	
	public Member deleteMember(int id)
	{
		Optional<Member>op= memberRepo.findById(id);
		if(op.isPresent())
		{
			Member db= op.get();
			memberRepo.delete(db);
			return db;
		}
		return null;
	}
	
	public List<Member> fetchAll()
	{
		List<Member> list= memberRepo.findAll();
		if(list.isEmpty()==false)
		{
			return list;
		}
		else
		{
			return null;
		}
	}
	
	public Member memberLogin(String email,String password)
	{
		Member member= memberRepo.findByEmail(email);
		if(member!=null)
		{
			if(member.getPassword()==password)
			{
				return member;
			}
			else
			{
				throw new NotFoundException("Password Mismatch");
			}
		}
		else
		{
			throw new NotFoundException("Member "+member.getEmail() +"not found");
		}
	}

	public Member fetchMemEmail(String email) 
	{
		Member member= memberRepo.findByEmail(email);
		if(member!=null)
		{
			return member;
		}
		else
		{
			throw new NotFoundException("Email not found");
		}
	}

	
}
