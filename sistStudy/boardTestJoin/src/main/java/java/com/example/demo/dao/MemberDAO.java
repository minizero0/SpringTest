package java.com.example.demo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.com.example.demo.entity.Member;

public interface MemberDAO extends JpaRepository<Member, String>{

	@Query(value = "select * from member where id = ?1 and pwd = ?2", nativeQuery = true)
	public Member login(String id, String pwd);
}
