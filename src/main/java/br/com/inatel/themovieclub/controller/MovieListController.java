package br.com.inatel.themovieclub.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
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
import br.com.inatel.themovieclub.model.Movie;
import br.com.inatel.themovieclub.model.MovieList;
import br.com.inatel.themovieclub.model.User;
import br.com.inatel.themovieclub.repository.MovieListRepository;
import br.com.inatel.themovieclub.service.ApiService;
import br.com.inatel.themovieclub.service.MovieDetails;

@RestController
@RequestMapping("/movieList")
public class MovieListController {
	
	@Autowired
	private MovieListRepository movieListRepository;
	
	@Autowired
	private ApiService api;
	
	@GetMapping
	public List<MovieList> list(Authentication auth) {
		return movieListRepository.findAll();
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
	public ResponseEntity<MovieListDto> create(@RequestBody @Valid MovieListForm form, UriComponentsBuilder uriBuilder) {
		MovieList movieList = form.toMovieList();
		for (Long movieId : form.getMovies()) {
			MovieDetails movieDetails = api.getMovieDetails(movieId); 
			movieList.addMovie(new Movie(movieDetails.getId(), movieDetails.getTitle(), movieDetails.getOverview()));
		}
		
		movieListRepository.save(movieList);
		URI uri = uriBuilder.path("/movielist/{id}").buildAndExpand(movieList.getId()).toUri();
		return ResponseEntity.created(uri).body(new MovieListDto(movieList)); 
	}
	
	@PutMapping("/{id}")
	@Transactional
	public ResponseEntity<MovieListDto> changeName(@PathVariable Long id, @RequestParam String newName) {
		Optional<MovieList> optional = movieListRepository.findById(id);
		if (optional.isPresent()) {
			MovieList movieList = optional.get();
			movieList.setName(newName);
			movieListRepository.save(movieList);
			return ResponseEntity.ok(new MovieListDto(movieList));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@PutMapping("/{movieListId}/movies/{movieId}")
	@Transactional
	public ResponseEntity<MovieListDto> addMovie(@PathVariable Long movieListId, @PathVariable Long movieId) {
		Optional<MovieList> optional = movieListRepository.findById(movieListId);
		if (optional.isPresent()) {
			MovieList movieList = optional.get();
			MovieDetails movieDetails = api.getMovieDetails(movieId);
			movieList.addMovie(new Movie(movieDetails.getId(), movieDetails.getTitle(), movieDetails.getOverview()));
			return ResponseEntity.ok(new MovieListDto(movieList));
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<MovieListDto> delete(@PathVariable Long id) {
		Optional<MovieList> optional = movieListRepository.findById(id);
		if (optional.isPresent()) {
			movieListRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	
	@DeleteMapping("/{movieListId/movies/{movieId}")
	@Transactional
	public ResponseEntity<MovieListDto> removeMovie(@PathVariable Long movieListId, @PathVariable Long movieId) {
		Optional<MovieList> optional = movieListRepository.findById(movieListId);
		if (optional.isPresent()) {
			optional.get().removeMovie(movieId);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
