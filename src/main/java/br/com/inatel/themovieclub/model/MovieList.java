package br.com.inatel.themovieclub.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class MovieList {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    @OneToMany(mappedBy = "movieList", cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @OnDelete(action = OnDeleteAction.CASCADE)
    private List<Movie> movies = new ArrayList<>();

    @ManyToOne
    private User owner;

    @NotBlank
    private String name;

    public MovieList(String name, User owner) {
        this.name = name;
        this.owner = owner;
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

}
