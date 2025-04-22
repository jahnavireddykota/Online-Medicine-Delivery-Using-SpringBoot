package com.jsp.capstoneProject.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.capstoneProject.entity.Drug;

public interface DrugRepo extends JpaRepository<Drug, Integer>
{
	Optional<Drug> findByName(String name);
}
