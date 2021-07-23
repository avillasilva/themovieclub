package br.com.inatel.themovieclub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Comment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String author;
	private String content;
	
//	@ManyToOne
//	private Post post;
	
	public Comment() {}
	
	public Comment(String author, String content) {
		this.author = author;
		this.content = content;
	}
	
	public Long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
	
	public String getAuthor() {
		return author;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
