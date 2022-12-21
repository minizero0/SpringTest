package com.example.demo.dao;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Board;

import jakarta.transaction.Transactional;

@Repository
public interface BoardDAO extends JpaRepository<Board, Integer> {
	
	//public List<Board> findAllByOrderByB_refDescB_stepAsc();
	
	//@Query("select b from Board b where b.no between ?1 and ?2 order by b.b_ref desc, b.b_step asc")
	@Query(value = "select no,b_level,b_ref,b_step,content,fname,hit,ip,pwd,regdate,title,writer,id from (select a.*, rownum n from (select * from Board order by b_ref desc, b_step) a) where n between ?1 and ?2", nativeQuery=true)
	public List<Board> selectAll(int start, int end);
	

	@Query("select nvl(max(no), 0)+1 from Board")
	int getNextNo();
	
	@Modifying
	@Query(value = "insert into Board b(b.no,b.title,b.writer,b.pwd,b.content,b.regdate,b.hit,b.ip,b.b_ref,b.b_step,b.b_level,b.fname,b.id) values(:#{#b.no},:#{#b.title},:#{#b.writer},:#{#b.pwd},:#{#b.content},sysdate,0,:#{#b.ip}, :#{#b.b_ref}, :#{#b.b_step}, :#{#b.b_level}, :#{#b.fname}, :#{#b.member.id})", nativeQuery = true)
	@Transactional
	public void insert(Board b);

	@Modifying
	@Query("update Board b set b.b_step = b.b_step + 1 where b.b_ref=?1 and b.b_step > ?2")
	@Transactional
	public void updateStep(int b_ref, int b_step);
	
	@Query("select no from Board where no = ?1")
	public int findByNo(int no);
	
	@Modifying
	@Query("delete Board b where b.no = ?1 and b.pwd = ?2")
	@Transactional
	public int deleteBoard(int no, String pwd);
	
	@Modifying
	@Query(value = "update Board b set b.title = :#{#b.title}, b.writer = :#{#b.writer}, b.fname = :#{#b.fname}, b.content = :#{#b.content} where b.no = :#{#b.no} and b.pwd = :#{#b.pwd}",nativeQuery = true)
	@Transactional
	public int update(Board b);
	
	
	@Query("select count(*) from Board")
	public int total();


	@Query(value = "select b.* from Board b where b.id = ?1", nativeQuery = true)
	public List<Board> selectById(String id);
	
}
