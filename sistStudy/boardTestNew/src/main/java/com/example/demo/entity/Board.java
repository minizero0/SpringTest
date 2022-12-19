package com.example.demo.entity;

import java.sql.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Data
@Table(name = "board")
public class Board {
	@Id
	private int no;
	
	private String title;
	private String writer;
	private String pwd;
	private String content;
	private Date regdate;
	private int hit;
	private String ip;
}
