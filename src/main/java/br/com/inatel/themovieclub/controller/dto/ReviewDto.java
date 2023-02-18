package br.com.inatel.themovieclub.controller.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    private Long id;
    private String title;
    private String content;

}
