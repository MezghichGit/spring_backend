package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Article;

public interface ArticleService {
	public List<Article> listProviders();
	public Article  addProvider(Article  article );
	public Article  updateProvider(int id, Article  article);
	public void deleteArticle (int id);
	public Optional<Article > getProvider(int id);
}
