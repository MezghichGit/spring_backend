package com.sip.ams.controllers;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.services.ProviderService;

@RestController
@RequestMapping("/api/providers/")
@CrossOrigin("*")
public class ProviderController {

	@Autowired
	ProviderService providerService;

	@GetMapping()
	public List<Provider> getProviders() {
		return this.providerService.listProviders();
	}

	/*
	 * @GetMapping("/") public Page<Provider> getProviders(int page, int size) {
	 * return this.providerService.pageProviders(page, size); }
	 */

	@GetMapping("/search/{nom}/{ville}")
	public List<Provider> getProviders(@PathVariable(value = "nom", required = false) String nom,
			@PathVariable(value = "ville", required = false) String ville) {
		return providerService.findByNomAndVille(nom, ville);

	}

	@GetMapping("/search/{nom}")
	public List<Provider> getProviders(@PathVariable("nom") String nom) {
		return this.providerService.findByNom(nom);
	}

	/*
	 * @PostMapping("/") public Provider addproviderToList(@RequestParam(name="id")
	 * int id,
	 * 
	 * @RequestParam(name="nom") String nom,
	 * 
	 * @RequestParam(name="email") String email,
	 * 
	 * @RequestParam(name="details") String details,
	 * 
	 * @RequestParam(name="ville") String ville,
	 * 
	 * @RequestParam(name="imageFile") MultipartFile file) throws IOException {
	 * return this.providerService.addProvider(id,nom,email,details,ville,file); }
	 */

	//@PostMapping("/addWithImage")
	@PostMapping
	public ResponseEntity<Provider> addproviderToList(@RequestParam(name = "id") int id,
			@RequestParam(name = "nom") String nom, @RequestParam(name = "email") String email,
			@RequestParam(name = "details") String details, @RequestParam(name = "ville") String ville,
			@RequestParam(name = "imageFile") MultipartFile file) throws IOException {
		return new ResponseEntity<>(this.providerService.addProvider(id, nom, email, details, ville, file),
				HttpStatus.CREATED);
	}

	// Autre retour avec un msg

	/*@PostMapping
	public ResponseEntity<Provider> createProvider(@RequestBody Provider provider) {
		Provider savedProvider = providerService.addProvider(provider);
		return new ResponseEntity<>(savedProvider, HttpStatus.CREATED);
	}*/

	/*
	 * @DeleteMapping("/{id}") public void deleteProvider(@PathVariable("id") int
	 * id) throws IOException { this.providerService.deleteProvider(id); }
	 */

	// Le type Void dans ResponseEntity<Void> indique
	// qu'il n'y a pas de corps dans la réponse. Void signifie
	// qu'il n'y a pas d'objet ou de contenu retourné, ce qui
	// est souvent utilisé pour des réponses telles que 204 No Content,
	// où il n'y a pas de données supplémentaires à renvoyer.

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteProvider(@PathVariable int id) throws IOException {
		boolean isDeleted = this.providerService.deleteProvider(id);
		if (isDeleted) {
			// Retourner un code 204 (No Content) pour une suppression réussie sans contenu
			return new ResponseEntity<>("Provider with id : " + id + " deleted", HttpStatus.NO_CONTENT);
		} else {
			// Si le provider n'existe pas, retournez un code 404 (Not Found)
			return new ResponseEntity<>("Provider with id : " + id + " not found", HttpStatus.NOT_FOUND);
		}
	}

	/*
	 * @GetMapping("/{id}") public Optional<Provider>
	 * getProvider(@PathVariable("id") int id) { return
	 * this.providerService.getProvider(id); }
	 */

	@GetMapping("/{id}")
	public ResponseEntity<Provider> getProvider(@PathVariable("id") int id) {
		Provider provider = this.providerService.getProvider(id).get();

		// Provider provider = null;
		if (provider == null)
			// throw new IllegalArgumentException("Argument invalide");
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		else
			return new ResponseEntity<>(provider, HttpStatus.OK);
	}

	@PutMapping("/{id}")
	public Provider updateProvider(@PathVariable int id, @RequestBody Provider providerDetails) {
		return this.providerService.updateProvider(id, providerDetails);
	}
	/*
	 * @ExceptionHandler(Exception.class) public ResponseEntity<String>
	 * handleException(Exception e) { // En cas d'erreur interne, retourner un code
	 * 500 (Internal Server Error) return new
	 * ResponseEntity<>("Une erreur interne est survenue : "+e.getMessage(),
	 * HttpStatus.INTERNAL_SERVER_ERROR); }
	 */
	/*
	 * static List<Provider> providers = new ArrayList<>(); static { Provider p1 =
	 * new Provider(1, "Toshiba", "toshiba@gmail.com"); Provider p2 = new
	 * Provider(2, "Samsung", "samsung@gmail.com"); Provider p3 = new Provider(3,
	 * "Orange", "orange@gmail.com"); providers.add(p1); providers.add(p2);
	 * providers.add(p3); }
	 * 
	 * @GetMapping("/names") public List<String> getNames() { List<String> names =
	 * List.of("Amine", "Ali", "Emna"); return names; }
	 * 
	 * @GetMapping("/list") public List<Provider> getProviders() { return providers;
	 * }
	 * 
	 * @PostMapping("/add") public Provider addproviderToList(@RequestBody Provider
	 * p) { // int size = providers.size(); // size++; // Provider p = new
	 * Provider(size,"Test "+size,"test"+size+"@gmail.com"); providers.add(p);
	 * return p; }
	 * 
	 * @DeleteMapping("/delete/{id}") public String
	 * deleteProvider(@PathVariable("id") int id) { int size = providers.size();
	 * providers.removeIf(p -> p.getId() == id); if ((providers.size()) == size - 1)
	 * { return "Deleted"; } else return "Problem occured when deleting";
	 * 
	 * }
	 * 
	 * @PutMapping("/update/{id}") public Provider
	 * updateProvider(@PathVariable("id") int id, @RequestBody Provider p) { int
	 * index = 0;
	 * 
	 * for (int i = 0; i < providers.size(); i++) { if (providers.get(i).getId() ==
	 * id) { index = i; break; } } Provider temp = new Provider(id, p.getNom(),
	 * p.getEmail()); providers.set(index, temp); return temp;
	 * 
	 * }
	 * 
	 * @GetMapping("/getById/{id}") public Optional<Provider>
	 * getProvider(@PathVariable("id") int id) { int index = -1; for (int i = 0; i <
	 * providers.size(); i++) { if (providers.get(i).getId() == id) { index = i;
	 * break; } }
	 * 
	 * if(index!= -1) { return Optional.of(providers.get(index)); } else return
	 * Optional.empty(); //throw new
	 * IllegalArgumentException("Provider introuvable"); }
	 */
}
