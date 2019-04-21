package com.projectmanager.projectmanagerservice.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@Component
public class UserDao {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(User user) {
		return userRepository.save(user);
	}
	
	public User updateUser(User user) {
		return userRepository.save(user);
	}
	
	public String deleteUser(Long userId) {
		userRepository.deleteById(userId);
		return "Success";
	}
	
	public List<User> viewUser(){
		return userRepository.findAll();
	}
}
