package com.jaybon.securityEx01.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CreationTimestamp;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// ORM - Object Relation Mapping - 알아서 데이터베이스 테이블 만들어 준다

@Data
@Entity // 이것을 토대로 데이터베이스 모델을 만들 수 있다.
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
	@Id // primary key를 걸어주는 어노테이션
	@GeneratedValue(strategy = GenerationType.IDENTITY) // 오라클은 시퀀스 전략, mysql은 오토인크리먼트 전략
	private int id;
	private String username;
	private String password;
	private String email;
	private String role; // ROLE_USER / ROLE_ADMIN
	//OAuth를 위해 구성한 추가 필드 2개
	private String provider;
	private String providerId;
	@CreationTimestamp
	private Timestamp createDate;
	
}
