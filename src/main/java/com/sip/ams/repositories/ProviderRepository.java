package com.sip.ams.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import com.sip.ams.entities.Provider;

public interface ProviderRepository extends MongoRepository<Provider, Integer>{

	//public List<Provider> findByNom(String nom);
	public List<Provider> findByNomIgnoreCaseContaining(String nom);
	public List<Provider> findByVille(String ville);
	public List<Provider> findByNom(String nom);
	 
	 
	 @Query("{ 'nom' : { '$regex' : ?0 , '$options' : 'i' }, 'ville' : { '$regex' : ?1 , '$options' : 'i' } }")
	 public List<Provider> findByNomAndVille(String nom, String ville);
	
}
