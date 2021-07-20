package br.com.inatel.steamclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.steamclub.model.Post;

public interface PostRepository extends JpaRepository<Post, Long> {

}
