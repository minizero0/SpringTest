package com.example.demo.vo;

import java.sql.Date;

import lombok.Data;

@Data
public class BoardVO {

		private int no;
		private String title;
		private String writer;
		private String pwd;
		private String content;
		private Date regdate;
		private int hit;
		private String ip;
		
		private int b_ref;
		private int b_step;
		private int b_level;
		
		private String fname;

}
