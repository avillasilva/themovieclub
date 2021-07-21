package br.com.inatel.steamclub.controller.dto;

import br.com.inatel.steamclub.model.Comment;

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
