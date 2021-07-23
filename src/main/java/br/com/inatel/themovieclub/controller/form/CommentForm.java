package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.Comment;
import br.com.inatel.themovieclub.repository.CommentRepository;

public class CommentForm {
    
    @NotNull @NotEmpty
    private String author;

    @NotNull @NotEmpty
    private String content;

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Comment toComment() {
        return new Comment(author, content);
    }

	public Comment update(Long id, CommentRepository commentRepository) {
		Comment comment = commentRepository.getById(id);
		comment.setContent(content);
		return comment;
	}
}
