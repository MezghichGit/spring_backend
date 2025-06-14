package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ArticleServiceImp implements ArticleService {

	private static final Logger logger = LoggerFactory.getLogger(ArticleServiceImp.class);

	@Autowired
	ArticleRepository articleRepository;
	@Autowired
	ProviderRepository providerRepository;

	@Override
	public List<Article> listArticles() {
		logger.info("Récupération de la liste des articles");
		return articleRepository.findAll();
	}

	@Override
	public Article addArticle(Article article) {
		// Charger le provider explicitement
		Optional<Provider> providerOptional = providerRepository.findById(article.getProvider().getId());

		if (providerOptional.isPresent()) {
			article.setProvider(providerOptional.get());
		}
		logger.info("Sauvegarde d'un nouvel article : " + article.getLibelle());
		return articleRepository.save(article);
	}

	@Override
	public Article updateArticle(int id, Article article) {
		// TODO Auto-generated method stub
		return null;
	}

	// Supprimer un article par ID
	@Override
	public boolean deleteArticle(int id) {
		if (articleRepository.existsById(id)) {
			articleRepository.deleteById(id);
			logger.info("Suppression de l'article avec succès avec ID : " +id);
			return true;
		}
		logger.info("Problème de suppression de l'article avec ID : "+id);
		return false;
	}

	@Override
	public Optional<Article> getArticle(int id) {
		// TODO Auto-generated method stub
		return Optional.empty();
	}

}
