package com.sip.ams.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sip.ams.entities.User;

public interface UserRepository  extends MongoRepository<User, Integer>{

}
