package br.com.inatel.themovieclub.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;

public class MovieListForm {
	
	@NotNull @NotEmpty
	private String name;
	
	@NotNull @NotEmpty
	private List<String> movies;
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public List<String> getMovies() {
		return movies;
	}
	
	public void setMovies(List<String> movies) {
		this.movies = movies;
	}
	
	public MovieList toMovieList(Long id, UserRepository userRepository) {
		User user = userRepository.findById(id).get(); 
		return new MovieList(name, user);
	}
}
