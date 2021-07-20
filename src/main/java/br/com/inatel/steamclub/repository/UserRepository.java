package br.com.inatel.steamclub.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.inatel.steamclub.model.User;

public interface UserRepository extends JpaRepository<User, Long> {
}