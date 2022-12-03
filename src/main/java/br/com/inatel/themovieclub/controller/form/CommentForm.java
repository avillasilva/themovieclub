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
import lombok.Data;

@Data
public class CommentForm {

    @NotNull
    @NotEmpty
    private String authorId;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private String reviewId;

    public Comment toComment(UserRepository userRepository, ReviewRepository reviewRepository) {
        Optional<User> optionalUser = userRepository.findById(Long.parseLong(authorId));
        Optional<Review> optionalReview = reviewRepository.findById(Long.parseLong(reviewId));
        return new Comment(optionalUser.get(), content, optionalReview.get());
    }

    public Comment update(Long id, CommentRepository commentRepository) {
        Comment comment = commentRepository.getById(id);
        comment.setComment(content);
        return comment;
    }

}
