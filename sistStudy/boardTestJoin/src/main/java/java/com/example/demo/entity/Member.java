package java.com.example.demo.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "member")
public class Member {
	@Id
	private String id;
	private String pwd;
	private String name;
	private String role;
	
	
	//create table member(id varchar2(20) primary key, pwd varchar2(20), name varchar2(20), role varchar2(20));
}
