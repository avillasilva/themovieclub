package br.com.inatel.themovieclub.controller.dto;

import java.util.List;
import java.util.stream.Collectors;

import br.com.inatel.themovieclub.model.MovieList;

public class MovieListDto {
	
	private Long id;
	private String name;

	public MovieListDto(MovieList movieList) {
		this.id = movieList.getId();
		this.name = movieList.getName();
	}
	
	public Long getId() {
		return id;
	}
	
	public String getName() {
		return name;
	}
	
	public static List<MovieListDto> toMovieListDto(List<MovieList> asList) {
		return asList.stream().map(MovieListDto::new).collect(Collectors.toList());
	}
}
