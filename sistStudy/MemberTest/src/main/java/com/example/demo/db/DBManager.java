package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.MemberVO;

public class DBManager {
	public static SqlSessionFactory sqlSessionFactory; 
	static {
		try {
			String resource = "com/example/demo/db/sqlMapConfig.xml";
			InputStream inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory =
			  new SqlSessionFactoryBuilder().build(inputStream);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	public static MemberVO findById(int no) {
		SqlSession session = sqlSessionFactory.openSession();
		MemberVO m = session.selectOne("member.findByNo", no);
		session.close();
		return m;
	}
	public static List<MemberVO> findAll() {
		SqlSession session = sqlSessionFactory.openSession();
		List<MemberVO> list = session.selectList("member.findAll");
		session.close();
		return list;
	}
	
}
