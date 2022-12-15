package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.vo.View_ListOrders;

@Repository
public interface View_ListOrdersDAO extends JpaRepository<View_ListOrders, Integer> {
	//public List<View_ListOrders> findByNameContaining(String name);
	
	@Query("select v from View_ListOrders v where v.name =?1")
	public List<View_ListOrders> searchName(String name);
}
