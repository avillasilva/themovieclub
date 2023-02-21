package br.com.inatel.themovieclub.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.inatel.themovieclub.exception.EntityNotFoundException;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.ReviewRepository;

@Service
public class ReviewService {

    private static final String REVIEW_NOT_FOUND_MESSAGE = "Review with id %d not found.";

    @Autowired
    private UserService userService;

    @Autowired
    private ReviewRepository reviewRepository;

    public Review read(Long reviewId) {
       return reviewRepository.findById(reviewId)
               .orElseThrow(() -> new EntityNotFoundException(formatMessage(reviewId)));
    }

    public Page<Review> search(Pageable pageable) {
        return reviewRepository.findAll(pageable);
    }

    @Transactional
    public Review save(Long userId, Review review) {
        User user = userService.read(userId);
        review.setAuthor(user);
        return reviewRepository.save(review);
    }

    @Transactional
    public void delete(Long reviewId) {
        try {
            reviewRepository.deleteById(reviewId);
            reviewRepository.flush();
        } catch (EmptyResultDataAccessException e) {
            throw new EntityNotFoundException(formatMessage(reviewId));
        }
    }

    private String formatMessage(Long reviewId) {
        return String.format(REVIEW_NOT_FOUND_MESSAGE, reviewId);
    }

}
