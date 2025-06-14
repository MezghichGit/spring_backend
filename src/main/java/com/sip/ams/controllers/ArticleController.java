package com.sip.ams.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@DeleteMapping("/{id}")
	public boolean deleteArticle(@PathVariable("id")int id) {
		return this.articleServiceImp.deleteArticle(id);
	}
	
	@PutMapping("/{id}")
	public Article updateArticle(@PathVariable("id")int id,@RequestBody Article article) {
		return this.articleServiceImp.updateArticle(id, article);
	}
	
	@GetMapping("/{id}")
	public Optional<Article> getArticleById(@PathVariable("id")int id) {
		return this.articleServiceImp.getArticleById(id);
	}

}
