package com.example.demo.db;

import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.CustomerVO;

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
	
	public static List<CustomerVO> findAll(){
		SqlSession session = sqlSessionFactory.openSession();
		List<CustomerVO> list = session.selectList("customer.findAll");
		session.close();
		return list;
	}
	
	public static CustomerVO findById(int custid){
		
		SqlSession session = sqlSessionFactory.openSession();
		CustomerVO cv = session.selectOne("customer.findById",custid);
		session.close();
		return cv;
	}

	public static int insertCustomer(CustomerVO c) {
		SqlSession session = sqlSessionFactory.openSession();
		int re = session.insert("customer.insert",c);
		session.commit();
		session.close();
		return re;
	}

	public static int updateCustomer(CustomerVO c) {
		// TODO Auto-generated method stub
		int re = 0;
		SqlSession session = sqlSessionFactory.openSession(true); //true는 autocommit하겠다는 의미
		re = session.update("customer.update",c);
		session.close();
		return re;
	}

	public static int deleteCustomer(int custid) {
		// TODO Auto-generated method stub
		int re = 0;
		SqlSession session = sqlSessionFactory.openSession(true);
		re = session.delete("customer.delete", custid);
		session.close();
		return re;
	}
	
	
}
