package com.sip.ams.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProviderTest {

    private Provider provider;

    @BeforeEach
    void setUp() {
        // Initialisation de l'objet Provider avant chaque test
        provider = new Provider(1, "Provider Test", "test@provider.com", "Some details");
    }

    @Test
    void testConstructorAndGetters() {
        // Vérifier les valeurs des attributs via les getters
        assertEquals(1, provider.getId());
        assertEquals("Provider Test", provider.getNom());
        assertEquals("test@provider.com", provider.getEmail());
        assertEquals("Some details", provider.getDetails());
    }

    @Test
    void testSetters() {
        // Tester les setters et s'assurer qu'ils modifient correctement les valeurs
        provider.setId(2);
        provider.setNom("Updated Provider");
        provider.setEmail("updated@provider.com");
        provider.setDetails("Updated details");

        assertEquals(2, provider.getId());
        assertEquals("Updated Provider", provider.getNom());
        assertEquals("updated@provider.com", provider.getEmail());
        assertEquals("Updated details", provider.getDetails());
    }

    @Test
    void testToString() {
        // Tester la méthode toString()
        String expected = "Provider [id=1, nom=Provider Test, email=test@provider.com, details=Some details]";
        assertEquals(expected, provider.toString());
    }

    @Test
    void testDefaultConstructor() {
        // Tester le constructeur sans paramètres
        Provider defaultProvider = new Provider();
        assertNotNull(defaultProvider);
        assertEquals(0, defaultProvider.getId());
        assertNull(defaultProvider.getNom());
        assertNull(defaultProvider.getEmail());
        assertNull(defaultProvider.getDetails());
    }

    @Test
    void testConstructorWithParameters() {
        // Tester le constructeur avec paramètres
        Provider customProvider = new Provider(3, "Custom Provider", "custom@provider.com", "Custom details");
        assertEquals(3, customProvider.getId());
        assertEquals("Custom Provider", customProvider.getNom());
        assertEquals("custom@provider.com", customProvider.getEmail());
        assertEquals("Custom details", customProvider.getDetails());
    }
}
