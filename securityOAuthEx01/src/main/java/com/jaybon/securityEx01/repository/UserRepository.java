package com.jaybon.securityEx01.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.jaybon.securityEx01.model.User;

// JpaRepository를 상속하면 자동 스캔됨.
public interface UserRepository extends JpaRepository<User, Integer>{

	//Jpa Naming 전략
	// SELECT * FROM user WHERE username = ?1
	User findByUsername(String username); // 함수이름에 맞게 쿼리가 동작한다
	
//	// SELECT * FROM user WHERE username = ?1 AND password = ?2
//	User findByUsernameAndPassword(String username, String password);
//	
//	@Query(value = "SELECT * FROM user", nativeQuery = true)
//	User 내맘대로();
	
//	직접 짜보는 쿼리
//	@Query(value = "SELECT * FROM user WHERE email =?1", nativeQuery = true)
//	Optional<User> mFindEmail(String email);
	
	// SELECT * FROM user WHERE email =?1
	Optional<User> findByEmail(String email);
	
	// SELECT * FROM user WHERE provider =?1 AND providerId =?2
	Optional<User> findByProviderAndProviderId(String provider, String ProviderId);
	
}
