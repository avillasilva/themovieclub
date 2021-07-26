package br.com.inatel.themovieclub.controller.dto;

import br.com.inatel.themovieclub.model.Review;

public class ReviewDto {
	
	private Long id;
	private String title;
	private String authorName;
	private String content;
	
	public ReviewDto(Review review) {
		this.id = review.getId();
		this.title = review.getTitle();
		this.authorName = review.getAuthor().getName();
		this.content = review.getContent();
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
