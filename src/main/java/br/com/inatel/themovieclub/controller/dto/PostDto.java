package br.com.inatel.themovieclub.controller.dto;

import br.com.inatel.themovieclub.model.Post;

public class PostDto {
	
	private Long id;
	private String title;
	private String authorName;
	private String content;
	
	public PostDto(Post post) {
		this.id = post.getId();
		this.title = post.getTitle();
		this.authorName = post.getAuthor();
		this.content = post.getContent();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getAuthorName() {
		return authorName;
	}

	public String getContent() {
		return content;
	}
}
