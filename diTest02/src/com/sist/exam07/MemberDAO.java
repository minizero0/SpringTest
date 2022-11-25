package com.sist.exam07;

public class MemberDAO {
	private String dbms;
	private Member member;
	
	public void setDbms(String dbms) {
		this.dbms = dbms;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public void insert() {
		System.out.println("dbsm:"+dbms + "에 등록하였습니다");
		member.info();
	}

}
