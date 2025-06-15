package com.sip.ams.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProviderServiceImp implements ProviderService {

	private static final Logger logger = LoggerFactory.getLogger(ProviderServiceImp.class);

	@Autowired
	ProviderRepository providerRepository;

	@Override
	public Page<Provider> pageProviders(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		logger.info("Récupération de la liste des providers");
		return providerRepository.findAll(pageable);
	}

	@Override
	public List<Provider> listProviders() {
		logger.info("Récupération de la liste des providers");
		return providerRepository.findAll();
	}

	@Override
	public Provider addProvider(int id, String nom, String email, String details, String ville, MultipartFile file)
			throws IOException {

		Provider provider = new Provider();
		provider.setId(id);
		provider.setNom(nom);
		provider.setDetails(details);
		provider.setEmail(email);
		provider.setVille(ville);
		// Appeler la méthode uploadImage pour sauvegarder l'image et récupérer le
		// chemin
		String photoPath = Utilitaire.uploadImage(file);

		// Mettre à jour l'attribut photo de l'article
		provider.setLogo(photoPath);
		logger.info("Sauvegarde d'un nouvel provider : " + provider.getId());

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
	public boolean deleteProvider(int id) throws IOException {
		if (providerRepository.existsById(id)) {
			Path path = null;
			logger.warn("Suppression du provider id {}", id);
			// Ajouter la suppression de l'image de provider
			//path = Paths.get(Utilitaire.root + "/" + providerRepository.findById(id).get().getLogo());
			// suppression l'image dans le dossier
			//Files.delete(path);
			providerRepository.deleteById(id);

			logger.info("Problème de suppression du provider avec ID : " + id);
			return true;
		}
		logger.info("Problème de suppression du provider avec ID : " + id);
		return false;

	}

	@Override
	public Optional<Provider> getProvider(int id) {
		logger.info("Recherche du provider id {}", id);
		return providerRepository.findById(id);
	}

	@Override
	public List<Provider> findByNom(String nom) {
		// TODO Auto-generated method stub
		// return providerRepository.findByNom(nom);
		return providerRepository.findByNomIgnoreCaseContaining(nom);
	}

	@Override
	public List<Provider> findByNomAndVille(String nom, String ville) {
		// TODO Auto-generated method stub
		return providerRepository.findByNomAndVille(nom, ville);
	}

	@Override
	public List<Provider> findByVille(String ville) {
		// TODO Auto-generated method stub
		return providerRepository.findByVille(ville);
	}

}
