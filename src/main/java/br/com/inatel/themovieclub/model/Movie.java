package br.com.inatel.themovieclub.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @ManyToOne
    private MovieList movieList;

    private Long originalId;
    private String title;
    private boolean watched;

    public Movie(Long originalId, String title, MovieList movieList) {
        this.originalId = originalId;
        this.title = title;
        this.movieList = movieList;
        this.watched = false;
    }

}
