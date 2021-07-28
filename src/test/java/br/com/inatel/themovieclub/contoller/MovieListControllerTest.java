package br.com.inatel.themovieclub.contoller;

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
	public void shouldCreateAMovieList() throws Exception {
		URI uri = new URI("/movieLists");
		String json = "{"
				+ "\"name\": \"test movie list correct\","
				+ "\"movies\": [ \"185\", \"1967\" ]"
				+ "}";
		
		mockMvc
		.perform(MockMvcRequestBuilders
				.post(uri)
				.content(json)
				.contentType(MediaType.APPLICATION_JSON))
		.andExpect(MockMvcResultMatchers
				.status().isCreated());
	}
	
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
}
