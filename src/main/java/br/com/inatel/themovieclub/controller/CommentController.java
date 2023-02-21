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

import br.com.inatel.themovieclub.controller.request.CommentRequest;
import br.com.inatel.themovieclub.controller.response.CommentReadResponse;
import br.com.inatel.themovieclub.mapper.CommentMapper;
import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.service.CommentService;

@RestController
@RequestMapping("/users/{userId}/reviews/{reviewId}/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Autowired
    private CommentMapper commentMapper;

    @GetMapping
    @Cacheable(value = "commentList")
    public Page<CommentReadResponse> search(@PageableDefault(sort = "id", size = 10) Pageable pageable, @PathVariable Long reviewId) {
        Page<Comment> comments = commentService.list(pageable, reviewId);
        return commentMapper.mapToCommentResponse(comments);
    }

    @PostMapping
    @CacheEvict(value = "commentList", allEntries = true)
    @ResponseStatus(HttpStatus.CREATED)
    public CommentReadResponse create(@RequestBody @Valid CommentRequest form, @PathVariable Long reviewId) {
        Comment comment = commentMapper.mapToComment(form);
        comment = commentService.save(comment);
        return commentMapper.mapToCommentResponse(comment);
    }

    @PutMapping("/{id}")
    @CacheEvict(value = "commentList", allEntries = true)
    public CommentReadResponse update(@PathVariable Long id, @RequestBody @Valid CommentRequest form) {
        Comment comment = commentService.read(id);
        commentMapper.mapToComment(form, comment);
        comment = commentService.save(comment);
        return commentMapper.mapToCommentResponse(comment);
    }

    @DeleteMapping("/{id}")
    @CacheEvict(value = "commentList", allEntries = true)
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Authentication authentication, @PathVariable Long id) {
        commentService.delete(id);
    }

}
