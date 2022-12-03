package br.com.inatel.themovieclub.controller.form;

import java.util.List;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.UserRepository;
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
