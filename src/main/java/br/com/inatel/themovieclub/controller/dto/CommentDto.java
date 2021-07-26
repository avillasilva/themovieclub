package br.com.inatel.themovieclub.controller.dto;

import java.util.List;

import org.springframework.data.domain.Page;

import br.com.inatel.themovieclub.model.Comment;

public class CommentDto {

    private Long id;
	private String authorName;
	private String content;

    public CommentDto(Comment comment) {
        this.id = comment.getId();
        this.authorName = comment.getAuthor().getName();
        this.content = comment.getComment();
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

	public static Page<CommentDto> toCommentDto(Page<Comment> comments) {
		return comments.map(CommentDto::new);
	}
}
