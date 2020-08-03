package com.cos.blog.repository;

import java.util.List;

import com.cos.blog.controller.dto.PostDetailRespDto;
import com.cos.blog.model.Post;

// 매퍼스캔 하기 때문에 자동으로 뜬다. 안띄우면 @Repository를 써야한다. JPA도 자동으로 붙여준다.
// 이 인터페이스를 구현한 익명클래스가 자동으로 만들어 질 것ㄴ
public interface PostRepository {
	
	public void save(Post requestPost);
	
	public List<Post> findAll();
	
	public PostDetailRespDto findById(int id);
	
	public Post findOne(int id);
	
	public void deleteById(int id);
	
	public void update(Post post);
	
}