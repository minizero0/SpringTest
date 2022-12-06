package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;
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

	public static List<BoardVO> findAll(HashMap<String, Object> map) {
		SqlSession session = sqlSessionFactory.openSession();
		List<BoardVO> list = session.selectList("board.findAll", map);
		session.close();
		return list;
	}
	
	public static int getNextNo() {
		int no = 0;
		SqlSession session = sqlSessionFactory.openSession();
		no = session.selectOne("board.getNextNo");
		session.close();
		return no;
	}
	
	public static int insert(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.insert("board.insert",b);
		session.close();
		return re;
	}
	
	public static BoardVO findByNo(int no) {
		BoardVO b = null;
		SqlSession session = sqlSessionFactory.openSession();
		b = session.selectOne("board.findByNo", no);
		session.close();
		return b;
	}

	public static int update(BoardVO b) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.update("board.update",b);
		session.close();
		return re;
	}

	public static int delete(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.update("board.delete",map);
		session.close();
		return re;
	}

	public static void updateStep(int b_ref, int b_step) {
		HashMap<String, Object> map = new HashMap<>();
		map.put("b_ref", b_ref);
		map.put("b_step", b_step);
		SqlSession session = sqlSessionFactory.openSession();
		session.update("board.updateStep",map);
		session.commit();
		session.close();
	}
	
	public static int getTotal() {
		int re = 0;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.selectOne("board.getTotal");
		session.close();
		return re;
	}

	public static int signUp(MemberVO m) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.insert("member.signUp", m);
		session.close();
		return re;
	}

	public static int logIn(HashMap<String, Object> map) {
		MemberVO m = null;
		int re = 0;
		SqlSession session = sqlSessionFactory.openSession();
		m = session.selectOne("member.logIn", map);
		if (m != null) {
			re = 1;
		}
		session.close();
		return re;
	}

	public static MemberVO findById(String id) {
		MemberVO m = null;
		SqlSession session = sqlSessionFactory.openSession();
		m = session.selectOne("member.findById",id);
		session.close();
		return m;
	}
}