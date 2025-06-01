package com.sip.ams.services;

import java.util.List;

import com.sip.ams.entities.User;

public interface UserService {
	public List<User> listUsers();
	public User addUser(User user);
}
