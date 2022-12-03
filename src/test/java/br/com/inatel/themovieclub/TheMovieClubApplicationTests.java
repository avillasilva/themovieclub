package br.com.inatel.themovieclub;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@ActiveProfiles("test")
public class TheMovieClubApplicationTests {

    @Test
    public void contextLoads() {
        assertThat(true).isTrue();
    }

}
