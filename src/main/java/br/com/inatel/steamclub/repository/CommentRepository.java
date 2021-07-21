package br.com.inatel.steamclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.steamclub.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
