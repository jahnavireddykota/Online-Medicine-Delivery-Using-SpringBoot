
package com.jsp.capstoneProject.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jsp.capstoneProject.entity.Member;

public interface MemberRepo extends JpaRepository<Member, Integer>
{
	Member findByEmail(String email);
}
