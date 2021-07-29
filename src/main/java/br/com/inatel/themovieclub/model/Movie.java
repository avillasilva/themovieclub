package br.com.inatel.themovieclub.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Movie {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne
    private MovieList movieList;
    
    private Long originalId;
    private String title;
    private boolean watched;
    
    public Movie() {}

    public Movie(Long originalId, String title, MovieList movieList) {
        this.originalId = originalId;
    	this.title = title;
        this.movieList = movieList;
        this.watched = false; 
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getOriginalId() {
		return originalId;
	}
    
    public void setOriginalId(Long originalId) {
		this.originalId = originalId;
	}
    
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    
    public MovieList getMovieList() {
		return movieList;
	}
    
    public void setMovieList(MovieList movieList) {
		this.movieList = movieList;
	}

    public boolean getWatched() {
    	return watched;
    }
    
    public void setWatched(boolean watched) {
    	this.watched = watched;
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, movieList, originalId, title, watched);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Movie other = (Movie) obj;
		return Objects.equals(id, other.id) && Objects.equals(movieList, other.movieList)
				&& Objects.equals(originalId, other.originalId) && Objects.equals(title, other.title)
				&& watched == other.watched;
	}
}
