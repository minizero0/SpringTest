package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.BookVO;

@Repository
public interface BookDAO extends JpaRepository<BookVO, Integer> {

	//도서명으로 검색하는 메소드 추가
	public List<BookVO> findByBookid(int bookid);
	public List<BookVO> findByBooknameContaining(String bookname);
	public List<BookVO> findByPublisherContaining(String publisher);
	public List<BookVO> findByPrice(int price);
	
//	static final String CASE_WHEN = "\nCASE\n"
//			+" WHEN :column = 'bookname' THEN b.bookname\n"
//			+" WHEN :column = 'publisher' THEN b.publisher\n"
//			+"END\n";
//	
//	static final String CASE_WHEN_NUM = "\nCASE\n"
//			+" WHEN :column = 'bookid' THEN b.bookid\n"
//			+" WHEN :column = 'price' THEN b.price\n"
//			+"END\n";
//	
//	@Query("select b from BookVO b where "+CASE_WHEN+" = :content order by :sort_col")
//	public List<BookVO> sort(String column, String sort_col,String content);
//	
//	@Query("select b from BookVO b where "+CASE_WHEN_NUM+" = :content order by :sort_col")
//	public List<BookVO> sort_num(String column, String sort_col,int content);
}
