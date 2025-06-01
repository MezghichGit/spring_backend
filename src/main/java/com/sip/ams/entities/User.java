package com.sip.ams.entities;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

@Document(collection = "users")
public class User {
	
	@Id
	private int id;
	private String nom;
	
	@Indexed(unique = true) // ✅ Crée un index unique sur email
	@Email(message = "Email invalide")
	@NotBlank(message = "Email requis")
	private String email;
	
	private String password;
	public int getId() {
		return id;
	}
	public User(int id, String nom, String email, String password) {
		super();
		this.id = id;
		this.nom = nom;
		this.email = email;
		this.password = password;
	}
	public User() {
		super();
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", nom=" + nom + ", email=" + email + ", password=" + password + "]";
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}

}
