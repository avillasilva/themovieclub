package br.com.inatel.themovieclub.contoller;

import static org.hamcrest.CoreMatchers.containsString;

import java.net.URI;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class MovieListControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void shouldReturn400WhenReceiveingEmptyFields() throws Exception {
		URI uri = new URI("/movieLists");
		String json = "{"
				+ "\"name\": \"\","
				+ "\"movies\": [ \"\" ]"
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status().is(400));
	}
	
	@Test
	public void shouldGetTheDetailsOfTheSpecifiedMovieList() throws Exception {
		URI uri = new URI("/movieLists/1");
		
		mockMvc
			.perform(MockMvcRequestBuilders.get(uri))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(containsString("id")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("name")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("movies")));
	}
	
	@Test
	public void shouldChangeTheNameOfTheSpecifiedMovieList() throws Exception {
		URI uri = new URI("/movieLists/1");
		String json = "{ \"name\": \"name changed\" }";
		
		mockMvc
			.perform(MockMvcRequestBuilders
					.put(uri)
					.content(json)
					.contentType(MediaType.APPLICATION_JSON))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(containsString("name changed")));
	}
	
	@Test
	public void shouldAddTheMovieToTheSpecifiedMovieList() throws Exception {
		URI uri = new URI("/movieLists/1/addMovie?movieId=1865");
		
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri))
			.andExpect(MockMvcResultMatchers.status().isOk())
			.andExpect(MockMvcResultMatchers.content().string(containsString("movies")))
			.andExpect(MockMvcResultMatchers.content().string(containsString("1865")));
	}
	
	@Test
	public void shouldMarkMovieAsWatched() throws Exception {
		URI uri = new URI("/movieLists/3/markAsWatched/3");
		
		mockMvc
			.perform(MockMvcRequestBuilders.put(uri))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shouldRemoveTheSpecifiedMovie() throws Exception {
		URI uri = new URI("/movieLists/1/removeMovie/1");
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete(uri))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
	
	@Test
	public void shoudDeleteTheSpecifidMovieList() throws Exception {
		URI uri = new URI("/movieLists/2");
		
		mockMvc
			.perform(MockMvcRequestBuilders.delete(uri))
			.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
