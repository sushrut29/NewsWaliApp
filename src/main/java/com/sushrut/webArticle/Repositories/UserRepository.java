package com.sushrut.webArticle.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushrut.webArticle.entity.User;

public interface UserRepository extends JpaRepository<User, String> {

}
