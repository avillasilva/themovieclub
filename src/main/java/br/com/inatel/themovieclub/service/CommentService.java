package br.com.inatel.themovieclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import br.com.inatel.themovieclub.exception.EntityNotFoundException;
import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.repository.CommentRepository;

@Component
public class CommentService {

    private static String COMMENT_NOT_FOUND_MESSAGE = "Comment with id %d not found.";

    @Autowired
    private CommentRepository repository;

<<<<<<< HEAD
    public Page<Comment> list(Pageable pageable, Long reviewId) {
=======
    public Page<Comment> search(Pageable pageable, Long reviewId) {
>>>>>>> ff7f2db208f1ed0e7e3e0bcdb0e2286e37711af5
        return repository.findAllByReviewId(pageable, reviewId);
    }

    public Comment read(Long commentId) {
        return repository.findById(commentId)
                .orElseThrow(() -> new EntityNotFoundException(formatMessage(commentId)));
    }

    @Transactional
    public Comment save(Comment comment) {
        return repository.save(comment);
    }

    @Transactional
    public void delete(Long commentId) {
        try {
            repository.deleteById(commentId);
            repository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(formatMessage(commentId));
        }
    }

    private String formatMessage(Long commentId) {
        return String.format(COMMENT_NOT_FOUND_MESSAGE, commentId);
    }

}
