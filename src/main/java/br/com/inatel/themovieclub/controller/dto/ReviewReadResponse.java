package br.com.inatel.themovieclub.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewReadResponse {

    private String title;
    private String content;
    private Movie movie;
    private UserReadResponse author;

}
