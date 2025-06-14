package com.sip.ams.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "providers")
public class Provider {
	
	@Id
	private int id;
	
	private String nom;
	private String email;
	private String details;
	private String logo;
	private String ville;
	
	public String getVille() {
		return ville;
	}
	public void setVille(String ville) {
		this.ville = ville;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	
	public String getDetails() {
		return details;
	}
	public void setDetails(String details) {
		this.details = details;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Provider() {
		super();
	}
	public Provider(int id, String nom, String email, String details) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.details = details;
	}
	@Override
	public String toString() {
		return "Provider [id=" + id + ", nom=" + nom + ", email=" + email + ", details=" + details +"]";
	}
	
}
