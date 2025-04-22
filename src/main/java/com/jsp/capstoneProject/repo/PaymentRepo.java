package com.jsp.capstoneProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.capstoneProject.entity.Payment;

public interface PaymentRepo extends JpaRepository<Payment, Integer>
{

}
