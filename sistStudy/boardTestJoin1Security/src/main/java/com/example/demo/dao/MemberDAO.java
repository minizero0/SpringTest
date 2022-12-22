package com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.entity.Member;

import jakarta.transaction.Transactional;

public interface MemberDAO extends JpaRepository<Member, String>{
	
	@Modifying
	@Query(value = "update member m set m.pwd = :#{#m.pwd}, m.name = :#{#m.name}, m.role = :#{#m.role} where m.id = :#{#m.id}", nativeQuery = true)
	@Transactional
	public int update(Member m);
	
	@Query(value = "select * from member where id = ?1 and pwd = ?2", nativeQuery = true)
	public Member login(String id, String pwd);
}
