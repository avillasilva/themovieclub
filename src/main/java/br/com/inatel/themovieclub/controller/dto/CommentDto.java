package br.com.inatel.themovieclub.controller.dto;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;

import br.com.inatel.themovieclub.model.Comment;

public class CommentDto {

    private Long id;
    private String authorName;
    private String content;
    private LocalDateTime createdAt;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.authorName = comment.getAuthor().getName();
        this.content = comment.getComment();
        this.createdAt = comment.getCreatedAt();
    }

    public Long getId() {
        return id;
    }

    public String getAuthorName() {
        return authorName;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public static Page<CommentDto> toCommentDto(Page<Comment> comments) {
        return comments.map(CommentDto::new);
    }

}
