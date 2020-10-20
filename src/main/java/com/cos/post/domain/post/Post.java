package com.cos.post.domain.post;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cos.post.domain.comment.Comment;
import com.cos.post.domain.user.User;
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
public class Post {
	@Id // 기본키 설정
	@GeneratedValue(strategy = GenerationType.IDENTITY) //해당 데이터베이스 번호증가 전략을 따라가기
	private int id;
	private String title;
	@Column(length = 100000)
	private String content;
	private int readCount;
	
	//User -> Posts를 호출 할 때만 호출
	@JoinColumn(name="userId")
	@ManyToOne // (fetch = FetchType.LAZY)
	private User user; //Foreign key
	
	
	@JsonIgnoreProperties({"user","post"})//무시하고 싶은 변수명
	@OneToMany(mappedBy = "post", fetch = FetchType.EAGER)
	private List<Comment> comments;
}
