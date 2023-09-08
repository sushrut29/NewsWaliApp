package com.sushrut.webArticle.Services;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sushrut.webArticle.Exception.ResourceNotFoundException;
import com.sushrut.webArticle.Repositories.NewsRepository;
import com.sushrut.webArticle.Repositories.UserRepository;
import com.sushrut.webArticle.entity.News;
import com.sushrut.webArticle.entity.User;

@Service
public class NewsService {
	
	@Autowired
	private NewsRepository newsRepository;
	
	@Autowired
	private UserRepository userRepository;
	
	public News createNews(News news , String userId) {
		
		User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		
		news.setDateAdded(new Date());
		news.setUser(u);
		
		News n = newsRepository.save(news);
		
		return n;
		
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
	
	public List<News> getNewsByUser(String userId){
		
		User u = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("user", "id", userId));
		List<News> news = newsRepository.findByUser(u);
		
		return news;
		
		
	}
}
