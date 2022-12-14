package com.example.demo.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.demo.db.DBManager;
import com.example.demo.vo.CustomerVO;

@Repository
public class CustomerDAO {
	
	public List<CustomerVO> findAll(){
		return DBManager.findAll();
	}
	
	public CustomerVO findById(int custid){
		return DBManager.findById(custid);
	}

	public int insert(CustomerVO c) {
		// TODO Auto-generated method stub
		return DBManager.insertCustomer(c);
	}

	public int update(CustomerVO c) {
		// TODO Auto-generated method stub
		return DBManager.updateCustomer(c);
	}

	public int delete(int custid) {
		// TODO Auto-generated method stub
		return DBManager.deleteCustomer(custid);
	} 
	

}
