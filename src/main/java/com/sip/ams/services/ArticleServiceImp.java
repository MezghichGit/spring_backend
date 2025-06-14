package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Article;
import com.sip.ams.repositories.ArticleRepository;

@Service
public class ArticleServiceImp implements ArticleService{

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImp.class);
	
	@Autowired
	ArticleRepository articleRepository;
	
	@Override
	public List<Article> listArticles() {
		logger.info("Récupération de la liste des articles");
		return articleRepository.findAll();
	}

	@Override
	public Article addArticle(Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Article updateArticle(int id, Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteArticle(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Optional<Article> getArticle(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
