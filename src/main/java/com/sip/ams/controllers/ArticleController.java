package com.sip.ams.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
	
	
	@GetMapping("/search/{libelle}")
	public List<Article> getArticles(@PathVariable("libelle")String libelle) {
		return this.articleServiceImp.findByLibelle(libelle);
	}
	
	@PostMapping("/")
	
	public ResponseEntity <Article> saveArticle(
			@RequestParam(name="id") int id,
			@RequestParam(name="libelle") String libelle,
			@RequestParam(name="prix") double prix,
			@RequestParam(name="provider") int provider,
			@RequestParam(name="imageFile") MultipartFile file) throws IOException {
		return new ResponseEntity<>(this.articleServiceImp.addArticle(id,libelle,prix,provider, file), HttpStatus.CREATED);
	}
	/*
	@DeleteMapping("/{id}")
	public boolean deleteArticle(@PathVariable("id")int id) throws IOException{
		return this.articleServiceImp.deleteArticle(id);
	}*/
	
	@DeleteMapping("/{id}")
    public ResponseEntity<String> deleteArticle(@PathVariable int id) throws IOException{
        boolean isDeleted = this.articleServiceImp.deleteArticle(id);
        if (isDeleted) {
            // Retourner un code 204 (No Content) pour une suppression réussie sans contenu
            return new ResponseEntity<>("Article  with id : "+id+" deleted", HttpStatus.NO_CONTENT);
        } else {
            // Si le provider n'existe pas, retournez un code 404 (Not Found)
            return new ResponseEntity<>("Article with id : "+id+" not found", HttpStatus.NOT_FOUND);
        }
    }
	
	@PutMapping("/{id}")
	public Article updateArticle(@PathVariable("id")int id,@RequestBody Article article) {
		return this.articleServiceImp.updateArticle(id, article);
	}
	
	/*
	@GetMapping("/{id}")
	public Optional<Article> getArticleById(@PathVariable("id")int id) {
		return this.articleServiceImp.getArticleById(id);
	}*/
	@GetMapping("/{id}")
	public ResponseEntity<Article> getArticleById(@PathVariable("id") int id) {
		Article article = this.articleServiceImp.getArticleById(id).get();
		
		//Provider provider = null;
		if (article == null)
			//throw new IllegalArgumentException("Argument invalide");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(article, HttpStatus.OK);
	}

}
