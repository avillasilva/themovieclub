package br.com.inatel.themovieclub.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.inatel.themovieclub.service.ApiService;
import br.com.inatel.themovieclub.service.MovieDetails;
import br.com.inatel.themovieclub.service.MovieList;

@RestController
@RequestMapping("/movieDatabase")
public class MovieDatabaseController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/search")
    public MovieList searchMovie(@RequestParam(required = true) String title, @RequestParam(required = true) String page) {
        return apiService.searchMovieByTitle(title, page);
    }

    @GetMapping("/movies")
    public MovieDetails getMovie(@RequestParam(required = true) Long movieId) {
        return apiService.getMovieDetails(movieId);
    }

}
