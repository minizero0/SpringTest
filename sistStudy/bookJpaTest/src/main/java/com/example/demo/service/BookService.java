package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.BookDAO;
import com.example.demo.vo.BookVO;

import lombok.Setter;

@Service
@Setter
public class BookService {
	
	@Autowired
	private BookDAO dao;
	
	public List<BookVO> findByBookname(String bookname){
		return dao.findByBooknameContaining(bookname);
	}
	public List<BookVO> findByBookid(int bookid){
		return dao.findByBookid(bookid);
	}
	public List<BookVO> findByPublisher(String publisher){
		return dao.findByPublisherContaining(publisher);
	}
	public List<BookVO> findByPrice(int price){
		return dao.findByPrice(price);
	}
	
	public List<BookVO> findAll(){
		return dao.findAll();
	}

	public void save(BookVO b) {
		dao.save(b);
	}
	
	public BookVO getOne(int bookid) {
		return dao.getOne(bookid);
	}

	public void delete(int bookid) {
		dao.deleteById(bookid);
	}
	
}
