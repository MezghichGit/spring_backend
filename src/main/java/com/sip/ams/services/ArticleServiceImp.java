package com.sip.ams.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

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
	public Article addArticle(int id, String libelle, double prix, int provider, MultipartFile file) throws IOException {
	//public Article addArticle(Article article, MultipartFile file) throws IOException {
		// Charger le provider explicitement
		Optional<Provider> providerOptional = providerRepository.findById(provider);

		Article article = new Article();
		article.setId(id);
		article.setLibelle(libelle);
		article.setPrix(prix);
		if (providerOptional.isPresent()) {
			article.setProvider(providerOptional.get());
		}
	     // Appeler la méthode uploadImage pour sauvegarder l'image et récupérer le chemin
        String photoPath = Utilitaire.uploadImage(file);

        // Mettre à jour l'attribut photo de l'article
        article.setPhoto(photoPath);
		logger.info("Sauvegarde d'un nouvel article : " + article.getLibelle());
		return articleRepository.save(article);
	}

	@Override
	// Mettre à jour un article existant
	public Article updateArticle(int id, Article articleDetails) {
		Optional<Article> articleOptional = articleRepository.findById(id);
		if (articleOptional.isPresent()) {
			Article article = articleOptional.get();
			article.setLibelle(articleDetails.getLibelle());
			article.setPrix(articleDetails.getPrix());
			// Charger le provider explicitement
			Optional<Provider> providerOptional = providerRepository.findById(article.getProvider().getId());

			if (providerOptional.isPresent()) {
				article.setProvider(providerOptional.get());
			}
			logger.info("Mise à jour de l'article avec succès, libellé: " + article.getLibelle());
			return articleRepository.save(article);
		}
		return null;
	}

	// Supprimer un article par ID
	@Override
	public boolean deleteArticle(int id) throws IOException {
		Path path = null;
		
		if (articleRepository.existsById(id)) {
			// Ajouter la suppression de l'image de l'article en question
			path = Paths.get(Utilitaire.root +"/"+ articleRepository.findById(id).get().getPhoto());
	        // Sauvegarder l'image dans le dossier
	        Files.delete(path);
	        articleRepository.deleteById(id);
			logger.info("Suppression de l'article avec succès avec ID : " + id);
			return true;
		}
		logger.info("Problème de suppression de l'article avec ID : " + id);
		return false;
	}

	@Override
	// Récupérer un article par son ID
	public Optional<Article> getArticleById(int id) {
		Optional<Article> articleOptional = articleRepository.findById(id);

		if (articleOptional.isPresent()) {
			// Forcer le chargement du provider
			Article article = articleOptional.get();
			Optional<Provider> providerOptional = providerRepository.findById(article.getProvider().getId());
			providerOptional.ifPresent(article::setProvider);
		}
		logger.info("Récupération de l'article avec succès : " + articleOptional.get().getLibelle());
		return articleOptional;
	}
	
	
    
    // Ajouter une photo à un article existant
    public Article addPhotoToArticle(int articleId, MultipartFile file) throws IOException {
        // Récupérer l'article par son ID
        Article article = articleRepository.findById(articleId).orElseThrow(() -> new RuntimeException("Article not found"));

        // Appeler la méthode uploadImage pour sauvegarder l'image et récupérer le chemin
        String photoPath = Utilitaire.uploadImage(file);

        // Mettre à jour l'attribut photo de l'article
        article.setPhoto(photoPath);

        // Sauvegarder l'article avec le chemin de l'image
        return articleRepository.save(article);
    }

}
