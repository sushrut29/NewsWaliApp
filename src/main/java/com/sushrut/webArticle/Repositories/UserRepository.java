package com.sushrut.webArticle.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushrut.webArticle.entity.User;

public interface UserRepository extends JpaRepository<User, String> {
	
	 Optional<User> findByEmail(String username);
	 
	 Boolean existsByEmail(String email);
}
