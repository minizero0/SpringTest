package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
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
			System.out.println("μμΈλ°μ:"+e.getMessage());
		}
	}
	
	
	public static List<DeptVO> listDept(){
		List<DeptVO> list = null;
		SqlSession session = sqlSessionFactory.openSession();
		list = session.selectList("dept.findAll");
		session.close();
		return list;
	}
	
	public static int insertDept(DeptVO d) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re= session.insert("dept.insert", d);
		session.commit();
		session.close();
		return re;
	}
	
	public static int updateDept(DeptVO d) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re= session.update("dept.update", d);
		session.commit();
		session.close();
		return re;
	}
	
	public static int deleteDept(int dno) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession();
		re = session.delete("dept.delete",dno);
		session.commit();
		session.close();
		return re;
	}

	public static int insertLog(HashMap<String, Object> map) {
		int re = -1;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.insert("log.insert",map);
		return re;
	}

	public static int nextNo() {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.selectOne("log.nextNo");
		return re;
	}
	
}