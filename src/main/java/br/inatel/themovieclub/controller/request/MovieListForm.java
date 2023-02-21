package br.inatel.themovieclub.controller.request;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.inatel.themovieclub.model.MovieList;
import br.inatel.themovieclub.model.User;
import br.inatel.themovieclub.repository.UserRepository;
import lombok.Data;

@Data
public class MovieListForm {

    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private List<String> movies;

    public MovieList toMovieList(Long id, UserRepository userRepository) {
        User user = userRepository.findById(id).get();
        return new MovieList(name, user);
    }

}
