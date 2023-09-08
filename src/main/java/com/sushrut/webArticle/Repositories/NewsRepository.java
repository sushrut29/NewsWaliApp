package com.sushrut.webArticle.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushrut.webArticle.entity.News;
import com.sushrut.webArticle.entity.User;

public interface NewsRepository extends JpaRepository<News, String>{
	
	List<News> findByUser(User user);

}
