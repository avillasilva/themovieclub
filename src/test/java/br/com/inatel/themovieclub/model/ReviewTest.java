package br.com.inatel.themovieclub.model;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

public class ReviewTest {
	
	private User user;
	private Review review;
	
	@BeforeEach
	void init() {
		this.user = new User("test user", "user@email.com", "password");
		this.review = new Review("test review", "content", this.user);
	}
	
	@Test
	void shouldThe() {
		Review mock = Mockito.mock(Review.class);
		List<Comment> comments = mock.getComments();
		Assertions.assertTrue(comments.isEmpty());
	}
}
