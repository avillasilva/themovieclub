package br.com.inatel.themovieclub.repository;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.inatel.themovieclub.model.Review;

@RunWith(SpringRunner.class)
@DataJpaTest
public class ReviewRepositoryTest {

    @Autowired
    private ReviewRepository reviewRepository;

    @Test
    public void shouldLoadReviewByTitle() {
        String title = "Review 1 - Luiz";
        Review review = reviewRepository.findByTitle(title);
        Assert.assertNotNull(review);
        Assert.assertEquals(title, review.getTitle());
    }

}
