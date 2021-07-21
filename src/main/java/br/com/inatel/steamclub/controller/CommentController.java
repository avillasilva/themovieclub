package br.com.inatel.steamclub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.steamclub.controller.dto.CommentDto;
import br.com.inatel.steamclub.controller.form.CommentForm;
import br.com.inatel.steamclub.model.Comment;
import br.com.inatel.steamclub.repository.CommentRepository;

@RestController
@RequestMapping("/comments")
public class CommentController {
    
    @Autowired
    private CommentRepository commentRepository;

    @GetMapping
    public List<Comment> list() {
        return commentRepository.findAll();
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
        Comment comment = form.toComment();
        commentRepository.save(comment);
        URI uri = uriBuilder.path("/comments/{id}").buildAndExpand(comment.getId()).toUri();
        return ResponseEntity.created(uri).body(new CommentDto(comment));
    }

    @PutMapping
    @Transactional
    public ResponseEntity<CommentDto> update(@PathVariable Long id, @RequestBody CommentUpdateForm form)
}