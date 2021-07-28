package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.repository.MovieListRepository;

public class MovieListUpdateForm {
	
	@NotNull @NotEmpty
	private String name;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public MovieList update(Long id, MovieListRepository movieListRepository) {
		MovieList movieList = movieListRepository.getById(id);
		movieList.setName(name);
		return movieList;
	}
}
