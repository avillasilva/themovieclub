package br.com.inatel.themovieclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long> {

}
