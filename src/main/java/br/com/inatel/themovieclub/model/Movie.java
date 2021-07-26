package br.com.inatel.themovieclub.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
public class Movie {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private MovieList movieList;
 
    @OneToMany
    private List<Genre> genres;
    
    private String title;
    private String overview;
    

    public Movie() {}

    public Movie(Long id, String title, String overview) {
        this.id = id;
        this.title = title;
        this.overview = overview;  
    }

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

    // public boolean watched() {
    //     return watched;
    // }

    // public void setWatched(boolean watched) {
    //     this.watched = watched;
    // }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}