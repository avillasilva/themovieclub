package br.com.inatel.themovieclub.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.repository.MovieListRepository;
import lombok.Data;

@Data
public class MovieListUpdateForm {

    @NotNull
    @NotEmpty
    private String name;

    public MovieList update(Long id, MovieListRepository movieListRepository) {
        MovieList movieList = movieListRepository.getById(id);
        movieList.setName(name);
        return movieList;
    }

}
