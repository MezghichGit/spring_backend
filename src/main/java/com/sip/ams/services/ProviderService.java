package com.sip.ams.services;

import java.util.List;
import java.util.Optional;

import com.sip.ams.entities.Provider;

public interface ProviderService {
	
	public List<Provider> listProviders();
	public Provider addProvider(Provider provider);
	public Provider updateProvider(int id, Provider provider);
	public void deleteProvider(int id);
	public Optional<Provider> getProvider(int id);

}
