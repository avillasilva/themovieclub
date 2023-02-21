package br.inatel.themovieclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.inatel.themovieclub.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

    Review findByTitle(String title);

}
