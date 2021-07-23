package br.com.inatel.themovieclub.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//Incorporate the api key into properties

@Component
public class ApiService {
	
	@Value("${api.key}")
	private static String apiKey;
	
	public static MovieList searchMovieByTitle(String query) {
		RestTemplate restTemplate = new RestTemplate();
		
		MovieList movieList = restTemplate.getForObject(
				"https://api.themoviedb.org/3/search/movie?api_key=48ee1fab81a490fcf35669de4869886f&language=en-US&query=" + query + "&page=1&include_adult=false", 
				MovieList.class);
		return movieList;
	}
	
	public static MovieDetails getMovieDetails(Long id) {
		RestTemplate restTemplate = new RestTemplate();
		
		MovieDetails movieDetails = restTemplate.getForObject(
				"https://api.themoviedb.org/3/movie/13355?api_key=48ee1fab81a490fcf35669de4869886f&language=en-US",
				MovieDetails.class); 
		
		return movieDetails;
	}
}
