package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.View_ListOrders;

@Repository
public interface View_ListOrdersDAO extends JpaRepository<View_ListOrders, Integer> {
	
	public List<View_ListOrders> findByNameContaining(String name);
	public List<View_ListOrders> findByBooknameContaining(String bookname);
	public List<View_ListOrders> findByOrderid(int orderid);
	
	//@Query("select v from View_ListOrders v where v.name =?1")
//	@Query("select v from View_ListOrders v where v.name = ?2 order by ?1")
//	public List<View_ListOrders> sort(String column, String keyword);
	
	static final String CASE_WEHN = "\nCASE\n"
			+" WHEN :field = 'name' THEN v.name\n"
			+" WHEN :field = 'bookname' THEN v.bookname\n"
			+" WHEN :field = 'orderdate' THEN v.orderdate\n"
			+" ELSE v.name\n"
			+"END\n";
	
	@Query("select v from View_ListOrders v where "+CASE_WEHN+" = :keyword order by :field")
	public List<View_ListOrders> sortTest(String field,String keyword);
}
