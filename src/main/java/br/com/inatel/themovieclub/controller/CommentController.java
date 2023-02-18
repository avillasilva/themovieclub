package br.com.inatel.themovieclub.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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

import br.com.inatel.themovieclub.controller.dto.CommentDto;
import br.com.inatel.themovieclub.controller.form.CommentForm;
import br.com.inatel.themovieclub.mapper.CommentMapper;
import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.service.CommentService;

@RestController
@RequestMapping("/users/{userId}/reviews/{reviewId}/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @Autowired
    private CommentMapper mapper;

    @GetMapping
    @Cacheable(value = "commentList")
    public Page<CommentDto> search(@PageableDefault(sort = "id", size = 10) Pageable pageable, @PathVariable Long reviewId) {
        Page<Comment> comments = service.search(pageable, reviewId);
        return mapper.mapToCommentResponse(comments);
    }

    @GetMapping("{id}")
    public CommentDto read(@PathVariable Long id) {
        Comment comment = service.read(id);
        return mapper.mapToCommentResponse(comment);
    }

    @PostMapping
    @CacheEvict(value = "commentList", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDto create(Authentication authentication, @RequestBody @Valid CommentForm form, @PathVariable Long reviewId, UriComponentsBuilder uriBuilder) {
        Comment comment = mapper.mapToComment(form);
        comment = service.save(comment);
        return mapper.mapToCommentResponse(comment);
    }

    @PutMapping("{id}")
    @CacheEvict(value = "commentList", allEntries = true)
    public CommentDto update(Authentication authentication, @PathVariable Long id, @RequestBody @Valid CommentForm form) {
        Comment comment = service.read(id);
        mapper.mapToComment(form, comment);
        comment = service.save(comment);
        return mapper.mapToCommentResponse(comment);
    }

    @DeleteMapping("{id}")
    @CacheEvict(value = "commentList", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication, @PathVariable Long id) {
        service.delete(id);
    }

}
