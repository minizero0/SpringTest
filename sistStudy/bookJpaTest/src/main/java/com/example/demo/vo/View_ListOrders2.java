package com.example.demo.vo;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "view_listorders2")
public class View_ListOrders2 {
	
	@EmbeddedId
	private OrdersViewID id;
	
	private int saleprice;
	private int price;
	private String orderdate;
}
