package com.sip.ams.controllers;

import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ArticleRepository;
import com.sip.ams.repositories.ProviderRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private ProviderRepository providerRepository;

    // 🔹 GET all articles
    @GetMapping
    public List<Article> getAllArticles() {
        return articleRepository.findAll();
    }

    // 🔹 GET article by ID
    @GetMapping("/{id}")
    public ResponseEntity<Article> getArticleById(@PathVariable String id) {
        return articleRepository.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // 🔹 POST: create article (avec idProvider)
    @PostMapping
    public ResponseEntity<Article> createArticle(@RequestBody Article article) {
        // Vérifie que le provider existe
        if (article.getProvider() != null && article.getProvider().getId() != -1) {
            Optional<Provider> providerOpt = providerRepository.findById(article.getProvider().getId());
            if (providerOpt.isPresent()) {
                article.setProvider(providerOpt.get());
                return ResponseEntity.ok(articleRepository.save(article));
            } else {
                return ResponseEntity.badRequest().body(null);
            }
        } else {
            return ResponseEntity.badRequest().body(null);
        }
    }

    // 🔹 PUT: update article
    @PutMapping("/{id}")
    public ResponseEntity<Article> updateArticle(@PathVariable String id, @RequestBody Article articleDetails) {
        Optional<Article> articleOpt = articleRepository.findById(id);
        if (articleOpt.isEmpty()) return ResponseEntity.notFound().build();

        Article article = articleOpt.get();
        article.setLibelle(articleDetails.getLibelle());
        article.setPrix(articleDetails.getPrix());

        if (articleDetails.getProvider() != null && articleDetails.getProvider().getId() != -1) {
            providerRepository.findById(articleDetails.getProvider().getId()).ifPresent(article::setProvider);
        }

        return ResponseEntity.ok(articleRepository.save(article));
    }

    // 🔹 DELETE article
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable String id) {
        if (!articleRepository.existsById(id)) return ResponseEntity.notFound().build();

        articleRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
