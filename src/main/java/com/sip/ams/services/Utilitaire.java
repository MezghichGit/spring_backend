package com.sip.ams.services;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class Utilitaire {
	public static final Path root = Paths.get(System.getProperty("user.dir") + "/src/main/resources/static/uploads");
	// Méthode pour gérer l'upload d'une image pour un article
    public static String uploadImage(MultipartFile file) throws IOException {
        // Créer un nom unique pour l'image
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        
        // Définir le chemin complet du fichier
        Path path = Paths.get(root +"/"+ fileName);

        // Sauvegarder l'image dans le dossier
        Files.write(path, file.getBytes());

        // Retourner le chemin de l'image
        return fileName;  // Cela stockera seulement le nom de l'image dans la base de données
    }
}
