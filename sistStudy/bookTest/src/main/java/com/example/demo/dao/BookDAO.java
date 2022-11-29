package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManaer;
import com.example.demo.vo.BookVO;
@Repository
public class BookDAO {
	
	public List<BookVO> findAll(){
		return DBManaer.findAll();
	}
}
