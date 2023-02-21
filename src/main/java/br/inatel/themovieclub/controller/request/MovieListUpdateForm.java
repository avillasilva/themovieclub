package br.inatel.themovieclub.controller.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.themovieclub.model.MovieList;
import br.inatel.themovieclub.repository.MovieListRepository;
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
