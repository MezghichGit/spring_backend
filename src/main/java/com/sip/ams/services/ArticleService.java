package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Article;

public interface ArticleService {
	public List<Article> listArticles();
	public Article  addArticle(Article  article );
	public Article  updateArticle(int id, Article  article);
	public boolean deleteArticle (int id);
	public Optional<Article > getArticleById(int id);
}
