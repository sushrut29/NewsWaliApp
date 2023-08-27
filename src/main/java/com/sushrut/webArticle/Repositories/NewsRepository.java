package com.sushrut.webArticle.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sushrut.webArticle.entity.News;

public interface NewsRepository extends JpaRepository<News, String>{

}
