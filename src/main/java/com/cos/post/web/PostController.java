package com.cos.post.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.post.domain.post.Post;
import com.cos.post.domain.post.PostRepositoty;
import com.cos.post.domain.user.User;
import com.cos.post.domain.user.UserRepositoty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class PostController {
	private final PostRepositoty postRepositoty;
	private final UserRepositoty userRepositoty;
	
	@PostMapping("/post")
	public String 글쓰기(@RequestBody Post post) {
		
		User userEntity = userRepositoty.findById(1).get();
		
		post.setReadCount(0);
		post.setUser(userEntity);
		postRepositoty.save(post);
		return "ok";
	}
	
	@GetMapping("/post")
	public List<Post> 글목록(){
		return postRepositoty.findAll();
	}
	
	@GetMapping("/post/{id}")
	public Post 글상세보기(@PathVariable int id) {
		Post post = postRepositoty.findById(id).get();
		System.out.println("post만 있음------------");
		post.getUser().getEmail();
		System.out.println("------lazy loading 실행");
		return post;
	}
}
