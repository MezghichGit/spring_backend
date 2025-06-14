package com.sip.ams.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.services.ArticleServiceImp;

@RequestMapping("/api/articles")
@CrossOrigin("*")
@RestController
public class ArticleController {
	
	@Autowired
	ArticleServiceImp articleServiceImp;
	
	@GetMapping("/")
	public List<Article> getArticles() {
		return this.articleServiceImp.listArticles();
	}
	
	
	@PostMapping("/")
	public Article saveArticle(@RequestBody Article article) {
		return this.articleServiceImp.addArticle(article);
	}

}
