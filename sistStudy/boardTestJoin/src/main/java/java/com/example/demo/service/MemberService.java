package java.com.example.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.com.example.demo.dao.MemberDAO;
import java.com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService {
	
	@Autowired
	private MemberDAO dao;
	
	public Member login(String id, String pwd) {
		return dao.login(id, pwd);
	}
	
}
