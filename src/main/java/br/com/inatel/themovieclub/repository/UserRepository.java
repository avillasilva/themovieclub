package br.com.inatel.themovieclub.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.themovieclub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

}