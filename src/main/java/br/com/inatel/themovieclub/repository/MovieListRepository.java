package br.com.inatel.themovieclub.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.MovieList;

public interface MovieListRepository extends JpaRepository<MovieList, Long> {
    Page<MovieList> findAllByOwnerId(Pageable pageable, Long id);
}
