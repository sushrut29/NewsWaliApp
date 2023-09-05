package com.sushrut.webArticle.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Exception.ResourceNotFoundException;
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

	public News getNewsById(String id) {
		News news = newsRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("user", "id", id));
		
		//Old flow
	//	Optional<News> news = newsRepository.findById(id);
		
		return news;
	}
}
