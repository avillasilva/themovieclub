package br.com.inatel.themovieclub.service;

import java.util.Arrays;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Movie {
	
	private Long id;
	private boolean adult;
	private int[] genre_ids;
	private String original_title;
	private String title;
	private Date release_date;
	
	public Movie() {}

	public boolean isAdult() {
		return adult;
	}

	public void setAdult(boolean adult) {
		this.adult = adult;
	}

	public int[] getGenre_ids() {
		return genre_ids;
	}

	public void setGenre_ids(int[] genre_ids) {
		this.genre_ids = genre_ids;
	}

	public String getOriginal_title() {
		return original_title;
	}

	public void setOriginal_title(String original_title) {
		this.original_title = original_title;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getRelease_date() {
		return release_date;
	}

	public void setRelease_date(Date release_date) {
		this.release_date = release_date;
	}

	@Override
	public String toString() {
		return "SearchResult [adult=" + adult + ",\n genre_ids=" + Arrays.toString(genre_ids) + ",\n original_title="
				+ original_title + ",\n title=" + title + ",\n release_date=" + release_date + "]";
	}
	
	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}
}
