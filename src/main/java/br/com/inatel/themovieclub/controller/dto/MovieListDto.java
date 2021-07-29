package br.com.inatel.themovieclub.controller.dto;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;

import br.com.inatel.themovieclub.model.Movie;
import br.com.inatel.themovieclub.model.MovieList;

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
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public List<MovieDto> getMovies() {
		return movies;
	}
	
	public static Page<MovieListDto> toMovieListDto(Page<MovieList> asList) {
		return asList.map(MovieListDto::new);
	}
}
