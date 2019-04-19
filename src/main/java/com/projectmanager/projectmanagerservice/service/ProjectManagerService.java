package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import com.projectmanager.projectmanagerservice.entity.User;

public interface ProjectManagerService {

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public String deleteUser(Long userId);
	
	public List<User> viewUser();
}
