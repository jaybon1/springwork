package com.jaybon.securityEx01.test;

import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.jaybon.securityEx01.model.User;
import com.jaybon.securityEx01.repository.UserRepository;

import net.bytebuddy.implementation.bytecode.Throw;

@RestController
public class OptionalControllerTest {
	
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/test/user/{id}")
	public User 옵셔널유저찾기(@PathVariable int id) {
// 첫번째 방법
//		Optional<User> userOptional = userRepository.findById(id);
//		
//		User user;
//		if(userOptional.isPresent()) {
//			user = userOptional.get();
//		} else {
//			user = new User();
//		}	
//		
// 두번째 방법
//		User user = userRepository.findById(id).orElseGet(()-> {
//
//				return User.builder().username("아무개").build();
//			
//		});
		
		// 세번째방법
		User user = userRepository.findById(id).orElseThrow(()->{
				
				return new NullPointerException("값이 없음");

		});
		
		return user;
	}
}
