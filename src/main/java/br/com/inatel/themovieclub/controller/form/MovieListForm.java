package br.com.inatel.themovieclub.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.repository.MovieListRepository;

public class MovieListForm {
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private List<Long> movies;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<Long> getMovies() {
		return movies;
	}
	
	public void setMovies(List<Long> movies) {
		this.movies = movies;
	}
	
	public MovieList toMovieList() {
		return new MovieList(name);
	}
}
