package com.sip.ams.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sip.ams.entities.Provider;

public interface ProviderRepository extends MongoRepository<Provider, Integer>{

}
