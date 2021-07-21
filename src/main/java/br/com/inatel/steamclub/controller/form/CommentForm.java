package br.com.inatel.steamclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.steamclub.model.Comment;

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
}
