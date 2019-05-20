package com.projectmanager.projectmanagerservice.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@Component
public class UserDao {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(UserRecord userRecord) {
		User user = new User();
		user.setFirstName(userRecord.firstName);
		user.setLastName(userRecord.lastName);
		user.setEmployeeId(userRecord.employeeId);
		return userRepository.save(user);
	}
	
	public User updateUser(ProjectManagerRecord projectManagerRecord) {
		User user = new User();
		user.setFirstName(projectManagerRecord.user.firstName);
		user.setLastName(projectManagerRecord.user.lastName);
		user.setEmployeeId(projectManagerRecord.user.employeeId);
		user.setProjectId(projectManagerRecord.project.projectId);
		user.setUserId(projectManagerRecord.user.userId);
		if(projectManagerRecord.taskId== null) {
			user.setTaskId(projectManagerRecord.parentTask.parentId);
		}
		else
		user.setTaskId(projectManagerRecord.taskId);
		return userRepository.save(user);
	}
	
	public String deleteUser(Long userId) {
		userRepository.deleteById(userId);
		return "Success";
	}
	
	public List<User> viewUser(){
		return userRepository.findAll();
	}
	public List<User> viewUserByFirstName(String firstName){
		return userRepository.findAllByFirstName(firstName);
	}
}
