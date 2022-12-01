package com.example.demo.db;

import java.io.InputStream;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import com.example.demo.vo.GoodsVO;

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
	
	public static List<GoodsVO> findAll(HashMap<String, Integer> map){
		SqlSession session = sqlSessionFactory.openSession();
		List<GoodsVO> list = session.selectList("goods.findAll", map);
		session.close();
		return list;
	}

	public static int insert(GoodsVO g) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.insert("goods.insert",g);
		session.close();
		return re;
	}

	public static GoodsVO findByNo(int no) {
		SqlSession session = sqlSessionFactory.openSession();
		GoodsVO g = session.selectOne("goods.findByNo", no);
		session.close();
		return g;
	}

	public static int update(GoodsVO g) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.update("goods.update", g);
		session.close();
		return re;
	}

	public static int delete(int no) {
		SqlSession session = sqlSessionFactory.openSession(true);
		int re = session.delete("goods.delete",no);
		session.close();
		return re;
	}
	
	public static int getTotal() {
		int cnt = 0;
		SqlSession session = sqlSessionFactory.openSession();
		cnt = session.selectOne("goods.total");
		session.close();
		return cnt;
	}
}
