package com.cos.post.domain.user;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.cos.post.domain.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	@Column(unique = true)
	private String username;
	private String password;
	private String email;
	
	//나는 연관관계의 주인이 아니야!(나는 FK를 가진 아이가 아니야)
	//조인하는 방법
	@JsonIgnoreProperties({"user","content"})//무시하고 싶은 변수명
	@OneToMany(mappedBy = "user", fetch = FetchType.EAGER)//Post 오브젝트의 user 변수
	private List<Post> posts;
}
