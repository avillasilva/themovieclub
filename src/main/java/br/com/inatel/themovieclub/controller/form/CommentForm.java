package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommentForm {

    @NotNull
    @NotEmpty
    private String authorId;

    @NotNull
    @NotEmpty
    private String content;

    @NotNull
    @NotEmpty
    private String reviewId;

}
