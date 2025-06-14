package com.sip.ams.repositories;
import org.springframework.data.mongodb.repository.MongoRepository;
import com.sip.ams.entities.Article;
import java.util.List;

public interface ArticleRepository extends MongoRepository<Article, Integer> {
    //List<Article> findByProviderId(String providerId); // rechercher les articles par fournisseur
}
