package br.inatel.themovieclub.controller.response;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.inatel.themovieclub.model.Movie;
import br.inatel.themovieclub.model.MovieList;
import lombok.Getter;

@Getter
public class MovieListDto {

    private Long id;
    private String name;
    private List<MovieDto> movies;

    public MovieListDto(MovieList movieList) {
        this.id = movieList.getId();
        this.name = movieList.getName();
        this.movies = new ArrayList<>();

        for (Movie movie : movieList.getMovies()) {
            movies.add(new MovieDto(movie));
        }
    }

    public static Page<MovieListDto> toMovieListDto(Page<MovieList> asList) {
        return asList.map(MovieListDto::new);
    }

}
