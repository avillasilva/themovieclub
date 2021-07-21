package br.com.inatel.steamclub.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Post {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String content;
	private String authorName;
	
//	@ManyToOne
//	private User author;
	
	@OneToMany
	private List<Comment> comments;
	
	private boolean isPublic;
	
	public Post() {}

	public Post(String title, String authorName, String content, boolean isPublic) {
		this.title = title;
		this.authorName = authorName;
		this.content = content;
		this.comments = new ArrayList<>();
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

	public String getAuthor() {
		return authorName;
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
