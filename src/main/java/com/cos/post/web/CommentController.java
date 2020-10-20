package com.cos.post.web;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.post.domain.comment.Comment;
import com.cos.post.domain.comment.CommentRepository;
import com.cos.post.domain.post.Post;
import com.cos.post.domain.post.PostRepositoty;
import com.cos.post.domain.user.User;
import com.cos.post.domain.user.UserRepositoty;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class CommentController {
	private final CommentRepository commentRepository;
	private final UserRepositoty userRepository;
	private final PostRepositoty postRepository;
	
	@PostMapping("/post/{postId}/comment")
	public String 댓글쓰기(@RequestBody Comment comment, 
			@PathVariable int postId) {
		
		User userEntity = userRepository.findById(1).get();
		Post postEntity = postRepository.findById(postId).get();
		
		comment.setUser(userEntity);
		comment.setPost(postEntity);
		commentRepository.save(comment);
		return "ok";
	}
}
