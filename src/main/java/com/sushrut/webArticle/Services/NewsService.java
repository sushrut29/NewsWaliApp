package com.sushrut.webArticle.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Repositories.NewsRepository;
import com.sushrut.webArticle.entity.News;

@Service
public class NewsService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	public News createNews(News news) {
		return newsRepository.save(news);
	}
	
	public List<News> getAllNews(){
		return newsRepository.findAll();
	}

}
