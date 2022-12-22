package com.example.demo.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.dao.MemberDAO;
import com.example.demo.entity.Member;

import lombok.Setter;

@Service
@Setter
public class MemberService implements UserDetailsService {
	
	
	@Autowired
	private MemberDAO memberDAO;
	
	public Member login(String id, String pwd) {
		return memberDAO.login(id, pwd);
	}
	
	public MemberService() {
		System.out.println("MemberService생성");
	}
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		System.out.println("사용자 로그인 처리");
		System.out.println("usertname:"+username);
		Optional<Member> obj = memberDAO.findById(username);
		UserDetails user = null;
		if(obj.isPresent()) {
			Member m = obj.get();
			user = User.builder()
			.username(username)
			.password(m.getPwd())
			.roles(m.getRole()).build();
		}else {
			throw new UsernameNotFoundException(username);
		}
							
		return user;
	}
}
