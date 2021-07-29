package br.com.inatel.themovieclub.controller.dto;

import org.springframework.data.domain.Page;

import br.com.inatel.themovieclub.model.Review;

public class ReviewDto {
	
	private Long id;
	private String title;
	private String content;
	
	public ReviewDto(Review review) {
		this.id = review.getId();
		this.title = review.getTitle();
		this.content = review.getContent();
	}

	public Long getId() {
		return id;
	}

	public String getTitle() {
		return title;
	}

	public String getContent() {
		return content;
	}

	public static Page<ReviewDto> toReviewDto(Page<Review> reviews) {
		return reviews.map(ReviewDto::new);
	}
}
