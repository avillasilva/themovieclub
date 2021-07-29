package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.inatel.themovieclub.controller.dto.MovieListDto;
import br.com.inatel.themovieclub.controller.form.MovieListForm;
import br.com.inatel.themovieclub.controller.form.MovieListUpdateForm;
import br.com.inatel.themovieclub.model.Movie;
import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.MovieListRepository;
import br.com.inatel.themovieclub.repository.MovieRepository;
import br.com.inatel.themovieclub.repository.UserRepository;
import br.com.inatel.themovieclub.service.ApiService;
import br.com.inatel.themovieclub.service.MovieDetails;

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
	public Page<MovieListDto> list(@PageableDefault(sort = "id", size = 10) Pageable pageable) {
		Page<MovieList> movieLists = movieListRepository.findAll(pageable);
		return MovieListDto.toMovieListDto(movieLists);
	}
	
	@GetMapping("{id}")
	public ResponseEntity<MovieListDto> detail(@PathVariable Long id) {
		Optional<MovieList> optional = movieListRepository.findById(id);
		if (optional.isPresent()) {
			MovieList movieList = optional.get();
			return ResponseEntity.ok(new MovieListDto(movieList));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<MovieListDto> create(Authentication authentication, @RequestBody @Valid MovieListForm form, UriComponentsBuilder uriBuilder) {
		User user = (User) authentication.getPrincipal();
		MovieList movieList = form.toMovieList(user.getId(), userRepository);
		
		for (String movieId : form.getMovies()) {
			MovieDetails movieDetails = api.getMovieDetails(Long.parseLong(movieId));
			Movie movie = new Movie(movieDetails.getId(), movieDetails.getTitle(), movieList);
			movieList.addMovie(movie);
			movieRepository.save(movie);
		}
		
		movieListRepository.save(movieList);
		URI uri = uriBuilder.path("/movielists/{id}").buildAndExpand(movieList.getId()).toUri();
		return ResponseEntity.created(uri).body(new MovieListDto(movieList)); 
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MovieListDto> changeName(@PathVariable Long id, @RequestBody @Valid MovieListUpdateForm form) {
		Optional<MovieList> optional = movieListRepository.findById(id);
		if (optional.isPresent()) {
			MovieList movieList = form.update(id, movieListRepository);
			return ResponseEntity.ok(new MovieListDto(movieList));
		}
		
		return ResponseEntity.notFound().build();
	}
	

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<MovieListDto> delete(@PathVariable Long id) {
		Optional<MovieList> optional = movieListRepository.findById(id);
		if (optional.isPresent()) {
			movieListRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{movieListId}/addMovie")
	@Transactional
	public ResponseEntity<MovieListDto> addMovie(@PathVariable Long movieListId, @RequestParam Long movieId) {
		Optional<MovieList> optional = movieListRepository.findById(movieListId);
		
		if (optional.isPresent()) {
			MovieList movieList = optional.get();
			MovieDetails movieDetails = api.getMovieDetails(movieId);
			Movie movie = new Movie(movieDetails.getId(), movieDetails.getTitle(), movieList);
			movieRepository.save(movie);
			return ResponseEntity.ok(new MovieListDto(movieList));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{movieListId}/removeMovie/{movieId}")
	@Transactional
	public ResponseEntity<MovieListDto> removeMovie(@PathVariable Long movieListId, @PathVariable Long movieId) {
		Optional<MovieList> movieListOptional = movieListRepository.findById(movieListId);
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (movieListOptional.isPresent() && movieOptional.isPresent()) {
			movieRepository.deleteById(movieId);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{movieListId}/markAsWatched/{movieId}")
	@Transactional
	public ResponseEntity<MovieListDto> markAsWatchedMovie(Authentication authentication, @PathVariable Long movieListId, @PathVariable Long movieId) {
		Optional<MovieList> movieListOptional = movieListRepository.findById(movieListId);
		Optional<Movie> movieOptional = movieRepository.findById(movieId);
		
		if (movieListOptional.isPresent() && movieOptional.isPresent()) {
			movieOptional.get().setWatched(true);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
