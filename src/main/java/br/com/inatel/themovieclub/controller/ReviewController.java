package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.Optional;

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
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.themovieclub.controller.dto.ReviewDto;
import br.com.inatel.themovieclub.controller.form.ReviewForm;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.ReviewRepository;
import br.com.inatel.themovieclub.repository.UserRepository;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Cacheable(value = "reviewList")
    public Page<ReviewDto> list(Authentication authentication,
            @PageableDefault(sort = "id", direction = Direction.DESC, size = 10) Pageable pageable) {
        Page<Review> reviews = reviewRepository.findAll(pageable);
        return ReviewDto.toReviewDto(reviews);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReviewDto> detail(@PathVariable Long id) {
        Optional<Review> review = reviewRepository.findById(id);

        if (!review.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(new ReviewDto(review.get()));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "reviewList", allEntries = true)
    public ResponseEntity<ReviewDto> create(Authentication authentication, @RequestBody @Valid ReviewForm form,
            UriComponentsBuilder uriBuilder) {
        User user = (User) authentication.getPrincipal();

        Review review = form.toReview(user.getId(), userRepository);
        reviewRepository.save(review);

        URI uri = uriBuilder.path("/reviews/{id}").buildAndExpand(review.getId()).toUri();
        return ResponseEntity.created(uri).body(new ReviewDto(review));
    }

    @PutMapping("/{id}")
    @Transactional
    @CacheEvict(value = "reviewList", allEntries = true)
    public ResponseEntity<ReviewDto> update(Authentication authentication, @PathVariable Long id, @RequestBody @Valid ReviewForm form) {
        User user = (User) authentication.getPrincipal();
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (!reviewOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (reviewOptional.get().getAuthor().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        Review review = form.update(id, reviewRepository);
        return ResponseEntity.ok(new ReviewDto(review));
    }

    @DeleteMapping("/{id}")
    @Transactional
    @CacheEvict(value = "reviewList", allEntries = true)
    public ResponseEntity<ReviewDto> delete(Authentication authentication, @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        Optional<Review> reviewOptional = reviewRepository.findById(id);

        if (!reviewOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (reviewOptional.get().getAuthor().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        reviewRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

}
