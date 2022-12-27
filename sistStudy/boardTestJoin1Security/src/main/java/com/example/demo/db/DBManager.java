package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.BoardVO;

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

	public static List<BoardVO> findAll() {
		SqlSession session = sqlSessionFactory.openSession();
		List<BoardVO> list = session.selectList("board.findAll");
		session.close();
		return list;
	}
}
