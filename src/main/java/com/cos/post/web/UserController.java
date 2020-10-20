package com.cos.post.web;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cos.post.domain.user.User;
import com.cos.post.domain.user.UserRepositoty;

import lombok.RequiredArgsConstructor;


@RequiredArgsConstructor //@Autowired 생략 방법 1
@RestController
public class UserController {
	
	private final UserRepositoty userRepositoty; //@Autowired 생략 방법2
	
	@PostMapping("/user")
	public String 회원가입(@RequestBody User user) {
		userRepositoty.save(user);
		return "ok";
	}
	
	@GetMapping("/user")
	public List<User> 유저목록(){
		List<User> users = userRepositoty.findAll();
		return users; // Jackson 라이브러리 - gettter실행
	}
	
	@GetMapping("/user/{id}")
	public User 유저상세(@PathVariable int id){
		User user = userRepositoty.findById(id).get();
		return user; // Jackson 라이브러리 - gettter실행
	}
}
