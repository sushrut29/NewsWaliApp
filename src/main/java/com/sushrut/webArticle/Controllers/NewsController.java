package com.sushrut.webArticle.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sushrut.webArticle.Services.NewsService;
import com.sushrut.webArticle.entity.News;

@RestController
@RequestMapping("/api/news")
public class NewsController {
	
	@Autowired
	private NewsService newsService;
	
	// create News
	
	@PostMapping("/")
	public News createNews(@RequestBody News news) {
		News n = newsService.createNews(news);
		return n;
	}
	
	//get all news
	
	@GetMapping("/")
	public List<News> getAllNews(){
		List<News> news = newsService.getAllNews();
		return news;
	}
	
	//get specific news by id
	
	@GetMapping("/{id}")
	public ResponseEntity<News> getNewsById(@PathVariable String id) {

		//Old flow
	/*	Optional<News> n = newsService.getNewsById(id);

		if(n.isPresent()) {
			News news = n.get();
			return new ResponseEntity<>(news, HttpStatus.OK) ;
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);*/
		
		//Updated flow
		try {
		
			News news = newsService.getNewsById(id);
			return new ResponseEntity<>(news, HttpStatus.OK);
		}
		catch (Exception e) {
			
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
			
	}
	
}
