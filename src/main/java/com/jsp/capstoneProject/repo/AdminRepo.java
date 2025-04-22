package com.jsp.capstoneProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.capstoneProject.entity.Admin;

public interface AdminRepo extends JpaRepository<Admin, Integer>
{
	Admin findByAdminemailid(String email);
}
