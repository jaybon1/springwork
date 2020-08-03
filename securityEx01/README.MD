# 스프링 시큐리티 기본 V1



### MySQL DB 및 사용자 생성

```sql
create user 'cos'@'%' identified by 'cos1234';
GRANT ALL PRIVILEGES ON *.* TO cos@'%';
create database security;
use security;
```





### SecurityConfig.java에서 권한 설정방법

```java
// SecurityConfig 에서  configure(HttpSecurity http) 함수 권한 설정법

//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')") // access() 권한 .hasAnyRole()
//			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN') and hasRole('ROLE_USER')")
			.antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
```





### 특정 주소 접근시 권한 및 인증( Controller 세팅 )

```java
// SecurityConfig 에 어노테이션 작성
    
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)

    
    // IndexController.java 에 어노테이션 작성
    
	//@PreAuthorize("hasRole('ROLE_MANAGER')") 구버전
	@Secured("ROLE_MANAGER") // 신버전
	@GetMapping("/manager")
	public @ResponseBody String manager() {
		return "매니저 페이지입니다";
	}
```



























