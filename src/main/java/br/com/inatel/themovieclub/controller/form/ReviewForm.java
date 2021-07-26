package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;

public class ReviewForm {
    
	@NotNull @NotEmpty @Length(min=5)
    private String title;

    @NotNull @NotEmpty
    private Long userId;

    @NotNull @NotEmpty
    private String content;
    
    @NotNull
    private boolean isPublic;

	public String getTitle() {
		return title;
	}

	public Long getUserId() {
		return userId;
	}

	public String getContent() {
		return content;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
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
		User user = userRepository.getById(userId);		
		return new Review(title, user, content, isPublic);
	}
}
