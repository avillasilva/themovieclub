package br.com.inatel.themovieclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {

}
