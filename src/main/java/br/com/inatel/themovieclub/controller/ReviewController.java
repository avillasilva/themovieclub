package br.com.inatel.themovieclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.themovieclub.controller.request.ReviewRequest;
import br.com.inatel.themovieclub.controller.response.ReviewReadResponse;
import br.com.inatel.themovieclub.controller.response.ReviewSearchResponse;
import br.com.inatel.themovieclub.mapper.ReviewMapper;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.service.ReviewService;

@RestController
@RequestMapping("/{userId}/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @Autowired
    private ReviewMapper reviewMapper;

    @GetMapping
    @Cacheable(value = "reviewList")
    public Page<ReviewSearchResponse> search(@PageableDefault(sort = "id", direction = Direction.DESC, size = 10) Pageable pageable) {
        Page<Review> reviews = reviewService.search(pageable);
        return reviewMapper.mapToReviewSearchResponse(reviews);
    }

    @GetMapping("/{id}")
    public ReviewReadResponse read(@PathVariable Long id) {
        Review review = reviewService.read(id);
        return reviewMapper.mapToReviewReadResponse(review);
    }

    @PostMapping
    @CacheEvict(value = "reviewList", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewReadResponse create(@PathVariable Long userId, @RequestBody @Valid ReviewRequest request) {
        Review review = reviewMapper.mapToReview(request);
        review = reviewService.save(userId, review);
        return reviewMapper.mapToReviewReadResponse(review);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "reviewList", allEntries = true)
    public ResponseEntity<ReviewReadResponse> update(Authentication authentication, @PathVariable Long userId, @PathVariable Long id, @RequestBody @Valid ReviewRequest form) {
        if (((User) authentication.getPrincipal()).getId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Review review = reviewService.read(id);
        reviewMapper.mapToReview(form, review);
        review = reviewService.save(userId, review);
        return ResponseEntity.ok(reviewMapper.mapToReviewReadResponse(review));
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "reviewList", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Void> delete(Authentication authentication, @PathVariable Long userId, @PathVariable Long id) {
        if (((User) authentication.getPrincipal()).getId() != userId) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        reviewService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
