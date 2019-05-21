package com.projectmanager.projectmanagerservice.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@Component
public class UserDao {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private UserRepository userRepository;
	
	public User addUser(UserRecord userRecord) throws ProjectManagerUserException {
		try {
			logger.info("Adding user ------" + userRecord.toString());
		User user = new User();
		user.setFirstName(userRecord.firstName);
		user.setLastName(userRecord.lastName);
		user.setEmployeeId(userRecord.employeeId);
		return userRepository.save(user);
		} catch(Exception e) {
			logger.info("Exception occured while adding user -- " + e.getMessage());
			throw new ProjectManagerUserException(500, "Error in adding user");
		}
	}
	
	public User updateUser(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException {
		try {
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
		}catch(Exception e) {
				logger.info("Exception occured while updating user -- " + e.getMessage());
				throw new ProjectManagerUserException(500, "Error in updating user");
		}
	}
	
	public String deleteUser(Long userId) throws ProjectManagerUserException {
		try {
		userRepository.deleteById(userId);
		return "Success";
		}catch(Exception e) {
			logger.info("Exception occured while deleting the user for userId --- "+ userId + ", error ---" + e.getMessage());
			throw new ProjectManagerUserException(500, "Error in deleting user");
		}
	}
	
	public List<User> viewUser() throws ProjectManagerUserException{
		try {
		return userRepository.findAll();
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching user list -- " + e.getMessage());
			throw new ProjectManagerUserException(500, "Error in fetching user list");
		}
	}
	
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException{
		try {
		return userRepository.findAllByFirstName(firstName);
		}
		catch(Exception e) {
			logger.info("Exception occured while fetching user list with first name -- " +firstName +" , error ----" + e.getMessage());
			throw new ProjectManagerUserException(500, e.getMessage());
		}
	}
}
