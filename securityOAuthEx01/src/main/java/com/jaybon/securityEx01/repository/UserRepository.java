package com.jaybon.securityEx01.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jaybon.securityEx01.model.User;

// JpaRepository를 상속하면 자동 스캔됨.
public interface UserRepository extends JpaRepository<User, Integer>{

	//Jpa Naming 전략
	// SELECT * FROM user WHERE username = ?
	User findByUsername(String username); // 함수이름에 맞게 쿼리가 동작한다
	
//	// SELECT * FROM user WHERE username = ? AND password = ?
//	User findByUsernameAndPassword(String username, String password);
//	
//	@Query(value = "SELECT * FROM user", nativeQuery = true)
//	User 내맘대로();
	
}
