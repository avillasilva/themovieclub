package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewForm {

    @NotNull
    @NotEmpty
    private String authorId;

    @NotNull
    @NotEmpty
    @Length(min = 5)
    private String title;

    @NotNull
    @NotEmpty
    private String content;

}
