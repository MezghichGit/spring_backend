package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

@Service
public class ProviderServiceImp implements ProviderService{

	@Autowired
	ProviderRepository providerRepository;
	
	@Override
	public List<Provider> listProviders() {
		return providerRepository.findAll();
	}

	@Override
	public Provider addProvider(Provider provider) {
		return providerRepository.save(provider);
	}

	@Override
	public Provider updateProvider(int id, Provider providerDetails) {
		 	Provider provider = providerRepository.findById(id).orElseThrow();
	        provider.setNom(providerDetails.getNom());
	        provider.setEmail(providerDetails.getEmail());
	        return providerRepository.save(provider);
	}

	@Override
	public void deleteProvider(int id) {
		providerRepository.deleteById(id);
		
	}

	@Override
	public Optional<Provider> getProvider(int id) {
		return providerRepository.findById(id);
	}

}
