package com.sip.ams.repositories;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sip.ams.entities.Provider;

public interface ProviderRepository extends MongoRepository<Provider, Integer>{

	//public List<Provider> findByNom(String nom);
	public List<Provider> findByNomIgnoreCaseContaining(String nom);
}
