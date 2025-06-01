package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProviderServiceImp implements ProviderService{

	private static final Logger logger = LoggerFactory.getLogger(ProviderServiceImp.class);
	
	@Autowired
	ProviderRepository providerRepository;
	
	@Override
	public List<Provider> listProviders() {
		logger.info("Récupération de la liste des providers");
		return providerRepository.findAll();
	}

	@Override
	public Provider addProvider(Provider provider) {
		logger.info("Ajout d'un provider : {}", provider);
		return providerRepository.save(provider);
	}

	@Override
	public Provider updateProvider(int id, Provider providerDetails) {
		    
		 	Provider provider = providerRepository.findById(id).orElseThrow();
	        provider.setNom(providerDetails.getNom());
	        provider.setEmail(providerDetails.getEmail());
	        provider.setDetails(providerDetails.getDetails());
	        logger.info("Mise à jour du provider id {}", id);
	        return providerRepository.save(provider);
	}

	@Override
	public void deleteProvider(int id) {
		logger.warn("Suppression du provider id {}", id);
		providerRepository.deleteById(id);
		
	}

	@Override
	public Optional<Provider> getProvider(int id) {
		logger.info("Recherche du provider id {}", id);
		return providerRepository.findById(id);
	}

}
