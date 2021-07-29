package br.com.inatel.themovieclub.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service
public class ApiService {
	public MovieList searchMovieByTitle(String query, String page) {
		RestTemplate restTemplate = new RestTemplate();
		
		MovieList movieList = restTemplate.getForObject(
				"https://api.themoviedb.org/3/search/movie?api_key=48ee1fab81a490fcf35669de4869886f&language=en-US&query=" + query + "&page="+ page + "&include_adult=false", 
				MovieList.class);
		return movieList;
	}
	
	public MovieDetails getMovieDetails(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		MovieDetails movieDetails = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/" + id + "?api_key=48ee1fab81a490fcf35669de4869886f&language=en-US",
				MovieDetails.class); 
		
		return movieDetails;
	}
}
