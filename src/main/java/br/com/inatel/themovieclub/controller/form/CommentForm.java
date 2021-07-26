package br.com.inatel.themovieclub.controller.form;

import java.util.Optional;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.CommentRepository;
import br.com.inatel.themovieclub.repository.ReviewRepository;
import br.com.inatel.themovieclub.repository.UserRepository;

public class CommentForm {
    
    @NotNull @NotEmpty
    private String authorId;

    @NotNull @NotEmpty
    private String content;
    
    @NotNull @NotEmpty
    private String reviewId;

    public String getAuthorId() {
        return authorId;
    }

    public void setAuthorId(String authorId) {
        this.authorId = authorId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
    
    public String getReviewId() {
		return reviewId;
	}
    
    public void setReviewId(String reviewId) {
		this.reviewId = reviewId;
	}

    public Comment toComment(UserRepository userRepository, ReviewRepository reviewRepository) {
    	Optional<User> optionalUser = userRepository.findById(Long.parseLong(authorId));
    	Optional<Review> optionalReview = reviewRepository.findById(Long.parseLong(reviewId));
    	
    	if (optionalUser.isPresent() && optionalReview.isPresent()) {
			return new Comment(optionalUser.get(), content, optionalReview.get());
		}
    	
    	return null;
    }

	public Comment update(Long id, CommentRepository commentRepository) {
		Comment comment = commentRepository.getById(id);
		comment.setComment(content);
		return comment;
	}
}
