package com.sip.ams.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mongodb.DuplicateKeyException;
import com.mongodb.MongoWriteException;
import com.sip.ams.entities.User;
import com.sip.ams.repositories.ProviderRepository;
import com.sip.ams.repositories.UserRepository;
@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepository;
	
	@Override
	public List<User> listUsers() {
		
		return this.userRepository.findAll();
	}

	@Override
	public User addUser(User user) {
		
		 try {
	            return userRepository.save(user);
	        } catch (MongoWriteException ex) {
	            throw new IllegalArgumentException("L'email \"" + user.getEmail() + "\" est déjà utilisé.");
	        }
	}

}
