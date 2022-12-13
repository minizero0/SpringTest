package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.DeptVO;

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
	
	public static List<DeptVO> findAll(){
		List<DeptVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("dept.findAll");
		session.close();
		return list;
		
	}

	public static DeptVO findByNo(int dno) {
		SqlSession session = sqlSessionFactory.openSession();
		DeptVO d = session.selectOne("dept.findById", dno);
		session.close();
		return d;
	}

	public static int delete(int dno) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.delete("dept.delete",dno);
		return re;
	}
	
}
