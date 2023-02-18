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
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.themovieclub.controller.dto.ReviewDto;
import br.com.inatel.themovieclub.controller.form.ReviewForm;
import br.com.inatel.themovieclub.mapper.ReviewMapper;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService service;

    @Autowired
    private ReviewMapper mapper;

    @GetMapping
    @Cacheable(value = "reviewList")
    public Page<ReviewDto> search(@PageableDefault(sort = "id", direction = Direction.DESC, size = 10) Pageable pageable) {
        Page<Review> reviews = service.search(pageable);
        return mapper.mapToReviewResponse(reviews);
    }

    @GetMapping("/{id}")
    public ReviewDto read(@PathVariable Long id) {
        Review review = service.read(id);
        return mapper.mapToReviewResponse(review);
    }

    @PostMapping
    @CacheEvict(value = "reviewList", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public ReviewDto create(Authentication authentication, @RequestBody @Valid ReviewForm form, UriComponentsBuilder uriBuilder) {
        User user = (User) authentication.getPrincipal();
        Review review = mapper.mapToReview(form);
        review.setAuthor(user);
        review = service.save(review);
        return mapper.mapToReviewResponse(review);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "reviewList", allEntries = true)
    public ReviewDto update(@PathVariable Long id, @RequestBody @Valid ReviewForm form) {
        Review review = service.read(id);
        mapper.mapToReview(form, review);
        review = service.save(review);
        return mapper.mapToReviewResponse(review);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "reviewList", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

}
