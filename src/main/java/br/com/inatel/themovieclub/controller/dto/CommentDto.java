package br.com.inatel.themovieclub.controller.dto;

import br.com.inatel.themovieclub.model.Comment;

public class CommentDto {

    private Long id;
	private String authorName;
	private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.authorName = comment.getAuthor();
        this.content = comment.getContent();
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
}
