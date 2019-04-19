package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.User;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public User addUser(User user) {
		return userDao.addUser(user);	
	}

	@Override
	public User updateUser(User user) {
		return userDao.updateUser(user);
	}

	@Override
	public String deleteUser(Long userId) {
		return userDao.deleteUser(userId);
		
	}

	@Override
	public List<User> viewUser() {
		return userDao.viewUser();
	}

}
