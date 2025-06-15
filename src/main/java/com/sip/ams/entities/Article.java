package com.sip.ams.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Document(collection = "articles")
public class Article {
	
	@Id
    private int id;

    public Article(int id, String libelle, double prix, Provider provider, String photo) {
		super();
		this.id = id;
		this.libelle = libelle;
		this.prix = prix;
		this.provider = provider;
		this.photo = photo;
	}
    public Article() {
		super();
	}
	private String libelle;
    private double prix;

    @DBRef
    //@DBRef // Chargement immédiat du provider
    private Provider provider; // ManyToOne avec Provider

    private String photo;
    
    // --- Getters & Setters ---

    public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public Provider getProvider() {
        return provider;
    }

    public void setProvider(Provider provider) {
        this.provider = provider;
    }
}
