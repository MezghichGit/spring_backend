package com.sip.ams.controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Provider;
import com.sip.ams.repositories.ProviderRepository;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {
	
	@Autowired
	ProviderRepository providerRepository;
	
	@GetMapping("/list")
	public List<Provider> getProviders() {
		return providerRepository.findAll();
	}

	
	@PostMapping("/add")
	public Provider addproviderToList(@RequestBody Provider p) {
		providerRepository.save(p);
		return p;
	}

	@DeleteMapping("/delete/{id}")
	public void deleteProvider(@PathVariable("id") int id) {
		providerRepository.deleteById(id);

	}
	
	
	@GetMapping("/getById/{id}")
	public Optional<Provider> getProvider(@PathVariable("id") int id) {
		return providerRepository.findById(id);
	}
	
	@PutMapping("/update/{id}")
    public Provider updateProvider(@PathVariable int id, @RequestBody Provider providerDetails) {
        Provider provider = providerRepository.findById(id).orElseThrow();
        provider.setNom(providerDetails.getNom());
        provider.setEmail(providerDetails.getEmail());
        return providerRepository.save(provider);
    }

	
	/*
	static List<Provider> providers = new ArrayList<>();
	static {
		Provider p1 = new Provider(1, "Toshiba", "toshiba@gmail.com");
		Provider p2 = new Provider(2, "Samsung", "samsung@gmail.com");
		Provider p3 = new Provider(3, "Orange", "orange@gmail.com");
		providers.add(p1);
		providers.add(p2);
		providers.add(p3);
	}

	@GetMapping("/names")
	public List<String> getNames() {
		List<String> names = List.of("Amine", "Ali", "Emna");
		return names;
	}

	@GetMapping("/list")
	public List<Provider> getProviders() {
		return providers;
	}

	@PostMapping("/add")
	public Provider addproviderToList(@RequestBody Provider p) {
		// int size = providers.size();
		// size++;
		// Provider p = new Provider(size,"Test "+size,"test"+size+"@gmail.com");
		providers.add(p);
		return p;
	}

	@DeleteMapping("/delete/{id}")
	public String deleteProvider(@PathVariable("id") int id) {
		int size = providers.size();
		providers.removeIf(p -> p.getId() == id);
		if ((providers.size()) == size - 1) {
			return "Deleted";
		} else
			return "Problem occured when deleting";

	}

	@PutMapping("/update/{id}")
	public Provider updateProvider(@PathVariable("id") int id, @RequestBody Provider p) {
		int index = 0;

		for (int i = 0; i < providers.size(); i++) {
			if (providers.get(i).getId() == id) {
				index = i;
				break;
			}
		}
		Provider temp = new Provider(id, p.getNom(), p.getEmail());
		providers.set(index, temp);
		return temp;

	}

	@GetMapping("/getById/{id}")
	public Optional<Provider> getProvider(@PathVariable("id") int id) {
		int index = -1;
		for (int i = 0; i < providers.size(); i++) {
			if (providers.get(i).getId() == id) {
				index = i;
				break;
			}
		}
	
		if(index!= -1)
		{
			return Optional.of(providers.get(index));
		}
		else return Optional.empty();
			//throw new IllegalArgumentException("Provider introuvable");
	}*/
}
