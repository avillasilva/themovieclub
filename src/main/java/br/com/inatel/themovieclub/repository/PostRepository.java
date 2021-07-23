package br.com.inatel.themovieclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
