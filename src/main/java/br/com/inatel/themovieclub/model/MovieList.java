package br.com.inatel.themovieclub.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class MovieList {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToMany
    private List<Movie> movies = new ArrayList<>();

    public MovieList() {}

    public MovieList(String name) {
        this.name = name;
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
    
    public void addMovie(Movie movie) {
        movies.add(movie);
    }
    
    public void removeMovie(Movie movie) {
        movies.remove(movie);
    }
}
