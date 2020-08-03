package com.cos.blog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cos.blog.controller.dto.PostDetailRespDto;
import com.cos.blog.model.Post;
import com.cos.blog.repository.PostRepository;

// Controller, Repository, Configuration, Service, Component
// RestController, Bean

@Service // IOC
public class PostService {

	@Autowired
	private PostRepository postRepository; // DI

	// 커밋이 필요한 기능(select가 아닌 것)은 @Transactional 붙여주자
	@Transactional
	public void 글쓰기(Post post) {
		postRepository.save(post);
	}
	
	@Transactional(readOnly = true)
	public List<Post> 목록보기() {
		return postRepository.findAll();
	}
	
	@Transactional(readOnly = true)
	public PostDetailRespDto 상세보기(int id) {
		return postRepository.findById(id);
	}
	
	@Transactional
	public void 게시물하나삭제하기(int id) {
		postRepository.deleteById(id);
	}
	
	@Transactional
	public void 게시물수정하기(Post post) {
		postRepository.update(post);
	}
}
