package br.com.inatel.themovieclub.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Review {
	
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@ManyToOne(fetch = FetchType.LAZY)
	private User author;
	
	@OneToMany(mappedBy = "review", cascade = CascadeType.ALL)
	private List<Comment> comments = new ArrayList<>();

	private String title;
	private String content;
	private boolean isPublic;
	
	public Review() {}

	public Review(String title, String content, boolean isPublic) {
		this.title = title;
		this.content = content;
		this.isPublic = isPublic;
	}

	public Long getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getAuthor() {
		return author;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void addComment(Comment comment) {
		comments.add(comment);
	}
	
	public void deleteComment(Long id) {
		for(Comment c : comments) {
			if(c.getId() == id) {
				comments.remove(c);
				return;
			}
		}
	}

	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}
}
