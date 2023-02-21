package br.com.inatel.themovieclub.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewSearchResponse {

    private String title;
    private String movie;
    private String author;

}
