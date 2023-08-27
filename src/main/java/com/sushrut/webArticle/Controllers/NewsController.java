package com.sushrut.webArticle.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
	
}
