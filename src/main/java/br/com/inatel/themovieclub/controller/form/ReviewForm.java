package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.ReviewRepository;
import br.com.inatel.themovieclub.repository.UserRepository;

public class ReviewForm {

	@NotNull @NotEmpty
	private String authorId;
    
	@NotNull @NotEmpty @Length(min=5)
    private String title;

    @NotNull @NotEmpty
    private String content;
    
    @NotNull
    private boolean isPublic;

	public String getTitle() {
		return title;
	}

	public String getAuthorId() {
		return authorId;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setAuthorId(String authorId) {
		this.authorId = authorId;
	}

	public void setContent(String content) {
		this.content = content;
	}
	
	public boolean isPublic() {
		return isPublic;
	}

	public void setPublic(boolean isPublic) {
		this.isPublic = isPublic;
	}

	public Review toReview(UserRepository userRepository) {
//		User user = userRepository.getById(userId);
		return new Review(title, content, isPublic);
	}

	public Review update(Long id, ReviewRepository reviewRepository) {
		Review review = reviewRepository.getById(id);
		review.setTitle(title);
		review.setContent(content);
		review.setPublic(isPublic);
		return review;
	}
}
