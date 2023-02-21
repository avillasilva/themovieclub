package br.inatel.themovieclub.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.inatel.themovieclub.controller.request.MovieForm;
import br.inatel.themovieclub.controller.request.MovieListForm;
import br.inatel.themovieclub.controller.request.MovieListUpdateForm;
import br.inatel.themovieclub.controller.response.MovieListDto;
import br.inatel.themovieclub.model.Movie;
import br.inatel.themovieclub.model.MovieList;
import br.inatel.themovieclub.model.User;
import br.inatel.themovieclub.moviedatabaseapi.ApiService;
import br.inatel.themovieclub.moviedatabaseapi.MovieDetails;
import br.inatel.themovieclub.repository.MovieListRepository;
import br.inatel.themovieclub.repository.MovieRepository;
import br.inatel.themovieclub.repository.UserRepository;

@RestController
@RequestMapping("/movieLists")
public class MovieListController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private MovieListRepository movieListRepository;

    @Autowired
    private MovieRepository movieRepository;

    @Autowired
    private ApiService api;

    @GetMapping
    public Page<MovieListDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable, Authentication authentication) {
        User user = (User) authentication.getPrincipal();

        Page<MovieList> movieLists = movieListRepository.findAllByOwnerId(pageable, user.getId());
        return MovieListDto.toMovieListDto(movieLists);
    }

    @GetMapping("{id}")
    public ResponseEntity<MovieListDto> detail(Authentication authentication, @PathVariable Long id) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> optional = movieListRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getOwner().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        MovieList movieList = optional.get();
        return ResponseEntity.ok(new MovieListDto(movieList));
    }

    @PostMapping
    @Transactional
    public ResponseEntity<MovieListDto> create(Authentication authentication, @RequestBody @Valid MovieListForm form,
            UriComponentsBuilder uriBuilder) {
        User user = (User) authentication.getPrincipal();
        MovieList movieList = form.toMovieList(user.getId(), userRepository);

        for (String movieId : form.getMovies()) {
            MovieDetails movieDetails = api.getMovieDetails(Long.parseLong(movieId));
            Movie movie = new Movie(movieDetails.getId(), movieDetails.getTitle(), movieList);
            movieList.addMovie(movie);
            movieRepository.save(movie);
        }

        movieListRepository.save(movieList);
        URI uri = uriBuilder.path("/movieLists/{id}").buildAndExpand(movieList.getId()).toUri();
        return ResponseEntity.created(uri).body(new MovieListDto(movieList));
    }

    @PutMapping("/{id}")
    @Transactional
    public ResponseEntity<MovieListDto> changeName(Authentication authentication, @PathVariable Long id,
            @RequestBody @Valid MovieListUpdateForm form) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> optional = movieListRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        MovieList movieList = form.update(id, movieListRepository);
        return ResponseEntity.ok(new MovieListDto(movieList));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<MovieListDto> delete(Authentication authenticaion, @PathVariable Long id) {
        User user = (User) authenticaion.getPrincipal();
        Optional<MovieList> optional = movieListRepository.findById(id);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        movieListRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/{movieListId}/movies")
    @Transactional
    public ResponseEntity<MovieListDto> addMovie(Authentication authentication, @PathVariable Long movieListId,
            @RequestBody MovieForm form) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> optional = movieListRepository.findById(movieListId);

        if (!optional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if (optional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        MovieList movieList = optional.get();
        MovieDetails movieDetails = api.getMovieDetails(Long.parseLong(form.getOriginalId()));
        Movie movie = new Movie(movieDetails.getId(), movieDetails.getTitle(), movieList);
        movieRepository.save(movie);
        return ResponseEntity.ok(new MovieListDto(movieList));
    }

    @DeleteMapping("/{movieListId}/movies/{movieId}")
    @Transactional
    public ResponseEntity<MovieListDto> removeMovie(Authentication authentication, @PathVariable Long movieListId,
            @PathVariable Long movieId) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> movieListOptional = movieListRepository.findById(movieListId);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (!movieOptional.isPresent() || movieOptional.get().getMovieList().getId() != movieListId) {
            return ResponseEntity.notFound().build();
        }

        if (movieListOptional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        movieRepository.deleteById(movieId);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{movieListId}/movies/{movieId}/watched")
    @Transactional
    public ResponseEntity<MovieListDto> watched(Authentication authentication, @PathVariable Long movieListId, @PathVariable Long movieId) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> movieListOptional = movieListRepository.findById(movieListId);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (!movieOptional.isPresent() || movieOptional.get().getMovieList().getId() != movieListId) {
            return ResponseEntity.notFound().build();
        }

        if (movieListOptional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        movieOptional.get().setWatched(true);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{movieListId}/movies/{movieId}/not-watched")
    @Transactional
    public ResponseEntity<MovieListDto> notWatched(Authentication authentication, @PathVariable Long movieListId,
            @PathVariable Long movieId) {
        User user = (User) authentication.getPrincipal();
        Optional<MovieList> movieListOptional = movieListRepository.findById(movieListId);
        Optional<Movie> movieOptional = movieRepository.findById(movieId);

        if (!movieOptional.isPresent() || movieOptional.get().getMovieList().getId() != movieListId) {
            return ResponseEntity.notFound().build();
        }

        if (movieListOptional.get().getId() != user.getId()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }

        movieOptional.get().setWatched(false);
        return ResponseEntity.ok().build();
    }

}
