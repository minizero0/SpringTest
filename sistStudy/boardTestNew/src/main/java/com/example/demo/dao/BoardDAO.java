package com.example.demo.dao;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {

	@Query("select nvl(max(no), 0)+1 from Board")
	int getNextNo();
	
	@Modifying
	@Query(value = "insert into Board b(b.no,b.title,b.writer,b.pwd,b.content,b.regdate,b.hit) values(:#{#b.no},:#{#b.title},:#{#b.writer},:#{#b.pwd},:#{#b.content},sysdate,0)", nativeQuery = true)
	@Transactional
	public void insert(Board b);

}
