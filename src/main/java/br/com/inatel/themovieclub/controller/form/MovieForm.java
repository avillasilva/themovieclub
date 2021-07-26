package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.Movie;

public class MovieForm {
	
	@NotNull @NotEmpty
	private Long id;

	@NotNull @NotEmpty
	private String title;
	
	@NotNull @NotEmpty
	private String overview;
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	
	public void setTitle(String title) {
		this.title = title;
	}
	
	public String getOverview() {
		return overview;
	}
	
	public void setOverview(String overview) {
		this.overview = overview;
	}
	
	public Movie toMovie() {
		return new Movie(id, title, overview); 
	}
}
