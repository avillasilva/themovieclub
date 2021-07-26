package br.com.inatel.themovieclub.model;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Comment {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private Review review;
	
	@ManyToOne
	private User author;
	
	private String comment;
	
	public Comment() {}
	
	public Comment(User author, String comment, Review review) {
		this.author = author;
		this.comment = comment;
		this.review = review;
	}
	
	public Long getId() {
		return id;
	}

	public String getComment() {
		return comment;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setComment(String content) {
		this.comment = content;
	}
	
	public Review getReview() {
		return review;
	}
	
	public void setReview(Review review) {
		this.review = review;
	}
}
