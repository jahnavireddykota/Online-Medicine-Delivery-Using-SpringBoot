package com.jsp.capstoneProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.capstoneProject.entity.Address;

public interface AddressRepo extends JpaRepository<Address, Integer>
{

}
