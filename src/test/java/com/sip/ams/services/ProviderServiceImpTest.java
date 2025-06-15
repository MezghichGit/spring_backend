package com.sip.ams.services;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
//@Transactional // Assurez-vous que les données sont supprimées après chaque test

public class ProviderServiceImpTest {
    @Autowired
    private ProviderServiceImp providerService; // Le service à tester

    @Autowired
    private ProviderRepository providerRepository; // Le repository

    private Provider provider;
    @BeforeEach
    void setUp() {
        // Initialisation d'un objet Provider pour les tests
        provider = new Provider(1, "Provider Test", "test@provider.com", "Some details");

        // Sauvegarde du provider dans la base de données avant chaque test
        providerRepository.save(provider);
    }

    
    @Test
    void testListProviders() {
        // Récupérer la liste des providers
        List<Provider> providers = providerService.listProviders();

        // Vérifier que la liste contient le provider que nous avons sauvegardé
        assertNotNull(providers);
        assertFalse(providers.isEmpty());
        assertEquals("Provider Test", providers.get(0).getNom());
    }
    
    @Test
    void testAddProvider() {
        // Créer un nouveau provider
        Provider newProvider = new Provider(2, "New Provider", "new@provider.com", "New details");

        // Ajouter le provider via le service
        Provider savedProvider = providerService.addProvider(newProvider);

        // Vérifier que le provider a bien été ajouté
        assertNotNull(savedProvider);
        assertEquals("New Provider", savedProvider.getNom());
        assertEquals("new@provider.com", savedProvider.getEmail());
    }


}
