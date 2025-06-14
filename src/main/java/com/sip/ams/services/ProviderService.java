package com.sip.ams.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Provider;

public interface ProviderService {
	
	public List<Provider> listProviders();
	//public Provider addProvider(Provider provider);
	public Provider  addProvider(int id,String nom,String email, String details, MultipartFile file)throws IOException;
	public void deleteProvider(int id) throws IOException;
	public Provider updateProvider(int id, Provider provider);
	//public void deleteProvider(int id);
	public Optional<Provider> getProvider(int id);
	public List<Provider> findByNom(String nom);

}
