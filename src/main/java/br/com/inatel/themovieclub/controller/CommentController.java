package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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

import br.com.inatel.themovieclub.controller.dto.CommentDto;
import br.com.inatel.themovieclub.controller.form.CommentForm;
import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.model.Review;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.CommentRepository;
import br.com.inatel.themovieclub.repository.ReviewRepository;
import br.com.inatel.themovieclub.repository.UserRepository;
import io.micrometer.core.ipc.http.HttpSender.Response;

@RestController
@RequestMapping("/users/{userIid}/reviews/{reviewId}/comments")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    @Cacheable(value = "commentList")
    public ResponseEntity<Page<CommentDto>> list(@PageableDefault(sort = "id", size = 10) Pageable pageable, @PathVariable Long reviewId) {
        if (!reviewRepository.existsById(reviewId)) {
            return ResponseEntity.notFound().build();
        }
        
        Page<Comment> comments = commentRepository.findAllByReviewId(pageable, reviewId);
    	
        return ResponseEntity.ok(CommentDto.toCommentDto(comments));
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> detail(@PathVariable Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        
        return ResponseEntity.ok(new CommentDto(optional.get()));
    }

    @PostMapping
    @Transactional
    @CacheEvict(value = "commentList", allEntries = true)
    public ResponseEntity<CommentDto> create(Authentication authentication, @RequestBody @Valid CommentForm form, @PathVariable Long reviewId, UriComponentsBuilder uriBuilder) {
        Optional<Review> optionalReview = reviewRepository.findById(reviewId);
    	
        if (!optionalReview.isPresent()) {
            return ResponseEntity.notFound().build();
        }
    
        Comment comment = form.toComment(userRepository, reviewRepository);
        commentRepository.save(comment);
        URI uri = uriBuilder.path("/comments/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDto(comment));
    }

    @PutMapping("{id}")
    @Transactional
    @CacheEvict(value = "commentList", allEntries = true)
    public ResponseEntity<CommentDto> update(Authentication authentication, @PathVariable Long id, @RequestBody @Valid CommentForm form) {
    	User user = (User) authentication.getPrincipal();
        Optional<Comment> optional = commentRepository.findById(id);

    	if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getAuthor().getId() == user.getId()) {
            return ResponseEntity.status(403).build();
        }
        
        Comment comment = form.update(id, commentRepository);
        return ResponseEntity.ok(new CommentDto(comment));
    }
    
    
    @DeleteMapping("{id}")
    @Transactional
    @CacheEvict(value = "commentList", allEntries = true)
    public ResponseEntity<CommentDto> delete(Authentication authentication, @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        Optional<Comment> optional = commentRepository.findById(id);
        
        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getAuthor().getId() == user.getId()) {
            return ResponseEntity.status(403).build();
        }

        commentRepository.delete(optional.get());
        return ResponseEntity.ok().build();
    }
}
