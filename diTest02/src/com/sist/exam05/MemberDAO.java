package com.sist.exam05;

public class MemberDAO {
	private String dbms;
	private Member member;
	public MemberDAO(String dbms, Member member) {
		super();
		this.dbms = dbms;
		this.member = member;
	}
	
	public void insert() {
		System.out.println("dbsm:"+dbms + "에 등록하였습니다");
		member.info();
	}
	

}
