package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.vo.View_ListOrders;

public interface View_ListOrdersDAO extends JpaRepository<View_ListOrders, Integer> {
	public List<View_ListOrders> findByNameContaining(String name);
}
