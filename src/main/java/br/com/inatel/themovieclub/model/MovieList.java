package br.com.inatel.themovieclub.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class MovieList {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @OneToMany(mappedBy = "movieList", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Movie> movies = new ArrayList<>();

    @ManyToOne
    private User owner;
   
    private String name;

    public MovieList() {}

    public MovieList(String name, User owner) {
        this.name = name;
        this.owner = owner;
    }
    
    public Long getId() {
		return id;
	}
 
    public void setId(Long id) {
		this.id = id;
	}
    
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovies() {
        return movies;
    }
    
    public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}
    
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    
    public void removeMovie(Long id) {
    	for (Movie movie : movies) {
    		if (movie.getId() == id) {
    			movies.remove(movie);
    		}
    	}
    }

	@Override
	public int hashCode() {
		return Objects.hash(id, movies, name, owner);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		MovieList other = (MovieList) obj;
		return Objects.equals(id, other.id) && Objects.equals(movies, other.movies) && Objects.equals(name, other.name)
				&& Objects.equals(owner, other.owner);
	}
}
