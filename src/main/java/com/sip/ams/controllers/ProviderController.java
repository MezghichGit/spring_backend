package com.sip.ams.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sip.ams.entities.Provider;

@RestController
@RequestMapping("/api/providers")
public class ProviderController {

	@GetMapping("/names")
	public List<String>getNames(){
		List<String> names = List.of("Amine","Ali","Emna");
		return names;
	}
	
	@GetMapping("/list")
	public List<Provider>getProvides(){
		Provider p1 = new Provider(1,"Toshiba","toshiba@gmail.com");
		Provider p2 = new Provider(2,"Samsung","samsung@gmail.com");
		Provider p3 = new Provider(3,"Orange","orange@gmail.com");
		List<Provider> providers = List.of(p1,p2,p3);
		return providers;
		
	}
}
