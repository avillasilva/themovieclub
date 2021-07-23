package br.com.inatel.themovieclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    
}
