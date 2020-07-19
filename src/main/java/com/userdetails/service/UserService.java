package com.userdetails.service;

import java.util.List;

import com.userdetails.model.User;


public interface UserService {
	
	public User saveUser(User user);
	public List<User> getAllUsers();
	public User getUserByID(Long id);
	public void deleteUserByID(Long id);

}
