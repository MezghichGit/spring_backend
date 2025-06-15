package com.sip.ams.services;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ArticleServiceImpTest {

    @Autowired
    private ArticleServiceImp articleService; // Le service à tester

    @Autowired
    private ArticleRepository articleRepository; // Le repository

    private Article article;
    @BeforeEach
    void setUp() {
        // Initialisation d'un objet Provider pour les tests
    	Provider provider = new Provider(1, "Provider Test", "test@provider.com", "Some details");
    	article = new Article(1, "A33", 1200, provider,"a33.png");

        // Sauvegarde du provider dans la base de données avant chaque test
    	articleRepository.save(article);
    }
    
    @Test
    void testListArticles() {
        // Récupérer la liste des articles
        List<Article> articles = articleService.listArticles();

        // Vérifier que la liste contient le provider que nous avons sauvegardé
        assertNotNull(articles);
        assertFalse(articles.isEmpty());
        assertEquals("A33", articles.get(0).getLibelle());
    }
    
    @Test
    void testAddArticle() {
        // Créer un nouvel article
    	Provider provider = new Provider(1, "Provider Test", "test@provider.com", "Some details");
        Article newArticle = new Article(100, "A70", 1900, provider,"a33.png");

        // Ajouter l'article via le service
        Article savedArticle = articleService.addArticle(newArticle);

        // Vérifier que le provider a bien été ajouté
        assertNotNull(savedArticle);
        assertEquals("A70", savedArticle.getLibelle());
        assertEquals(1900, savedArticle.getPrix());
    }

}
