package com.sip.ams.entities;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
public class ArticleTest {
	private Article article;
	private Provider provider;
	
	  @BeforeEach
	    void setUp() {
	        // Initialisation de l'objet Provider avant chaque test
		    provider = new Provider(1, "Samsung", "Samsung@gmail.com", "Some details");
	        article = new Article(2, "Samsung A33", 1200, provider,"samsungA33.png");
	    }
	  
	  @Test
	    void testConstructorAndGetters() {
	        // Vérifier les valeurs des attributs via les getters
	        assertEquals(2, article.getId());
	        assertEquals(1, article.getProvider().getId());
	    }
}
