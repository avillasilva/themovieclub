package br.com.inatel.themovieclub.controller.dto;

import br.com.inatel.themovieclub.model.Movie;
import lombok.Getter;

@Getter
public class MovieDto {

    private Long id;
    private Long originalId;
    private String title;
    private boolean watched;

    public MovieDto(Movie movie) {
        this.id = movie.getId();
        this.originalId = movie.getOriginalId();
        this.title = movie.getTitle();
        this.watched = movie.isWatched();
    }

}
