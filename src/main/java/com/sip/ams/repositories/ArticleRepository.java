package com.sip.ams.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sip.ams.entities.Article;
import com.sip.ams.entities.Provider;

import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, Integer> {
    //List<Article> findByProviderId(String providerId); // rechercher les articles par fournisseur
	
	public List<Article> findByLibelleIgnoreCaseContaining(String libelle);
}
