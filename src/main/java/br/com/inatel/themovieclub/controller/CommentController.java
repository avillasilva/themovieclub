package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
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
import br.com.inatel.themovieclub.repository.CommentRepository;
import br.com.inatel.themovieclub.repository.ReviewRepository;
import br.com.inatel.themovieclub.repository.UserRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;
    
    @Autowired
    private ReviewRepository reviewRepository;
    
    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public Page<CommentDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable) {
    	Page<Comment> comments = commentRepository.findAll(pageable);
    	
        return CommentDto.toCommentDto(comments);
    }

    @GetMapping("{id}")
    public ResponseEntity<CommentDto> detail(@PathVariable Long id) {
        Optional<Comment> optional = commentRepository.findById(id);
        if (optional.isPresent()) {
            return ResponseEntity.ok(new CommentDto(optional.get()));
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    @Transactional
    public ResponseEntity<CommentDto> create(@RequestBody @Valid CommentForm form, UriComponentsBuilder uriBuilder) {
        Comment comment = form.toComment(userRepository, reviewRepository);
        commentRepository.save(comment);
        URI uri = uriBuilder.path("/comments/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDto(comment));
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody @Valid CommentForm form) {
    	Optional<Comment> optional = commentRepository.findById(id);
    	if (optional.isPresent()) {
			Comment comment = form.update(id, commentRepository);
			return ResponseEntity.ok(new CommentDto(comment));
		}
    	return ResponseEntity.notFound().build();
    }
    
    
    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<CommentDto> delete(@PathVariable Long id) {
    	Optional<Comment> optional = commentRepository.findById(id);
    	if (optional.isPresent()) {
			commentRepository.delete(optional.get());
			return ResponseEntity.ok().build();
		}
    	
    	return ResponseEntity.notFound().build();
    }
}
