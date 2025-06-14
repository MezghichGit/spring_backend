package com.sip.ams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Article;

public interface ArticleService {
	public List<Article> listArticles();
	//public Article  addArticle(Article  article, MultipartFile file ) throws IOException;
	public Article  addArticle(int id, String libelle, double prix, int provider, MultipartFile file)throws IOException;
	public Article  updateArticle(int id, Article  article);
	public boolean deleteArticle (int id)throws IOException;
	public Optional<Article > getArticleById(int id);
}
