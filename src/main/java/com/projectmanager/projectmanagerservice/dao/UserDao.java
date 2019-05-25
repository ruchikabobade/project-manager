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
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addUser() , action : adding user , data : "+  userRecord.toString() + "}");
		try {
		User user = new User();
		user.setFirstName(userRecord.firstName);
		user.setLastName(userRecord.lastName);
		user.setEmployeeId(userRecord.employeeId);
		return userRepository.save(user);
		} catch(Exception e) {
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : addUser() , action : adding user , errorMessage : " +e.getLocalizedMessage() + "}");
			throw new ProjectManagerUserException(500, "Error in adding user");
		}
	}
	
	public User updateUser(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException {
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateUser() , action : updating user , data : "+  projectManagerRecord.toString() + "}");
		try {
		User user = new User();
		user.setFirstName(projectManagerRecord.user.firstName);
		user.setLastName(projectManagerRecord.user.lastName);
		user.setEmployeeId(projectManagerRecord.user.employeeId);
		user.setProjectId(projectManagerRecord.project.projectId);
		user.setUserId(projectManagerRecord.user.userId);
		if(projectManagerRecord.taskId != 0) {
			user.setTaskId(projectManagerRecord.taskId);
		}
		return userRepository.save(user);
		}catch(Exception e) {
			logger.info("{ loggerType : error , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : updateUser() , action : updating user , errorMessage : " +e.getLocalizedMessage() + "}");
				throw new ProjectManagerUserException(500, "Error in updating user");
		}
	}
	
	public String deleteUser(Long userId) throws ProjectManagerUserException {
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : deleteUser() , action : deleting user for given userId , data : "+  userId + "}");
		userRepository.deleteById(userId);
		return "Success";
	}
	
	public List<User> viewUser() throws ProjectManagerUserException{
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUser() , action : fetching the list of users , data :  , }");
		return userRepository.findAll();
	}
	
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException{
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : viewUserByFirstName() , action : Fetching user based on firstName  , data : "+  firstName + "}");
		return userRepository.findAllByFirstName(firstName);
	}
	
	public User getUserByProjectId(Long projectId) throws ProjectManagerUserException{
		logger.info("{ loggerType : info , loggedBy : " +this.getClass().getSimpleName()+" loggingMethod : getUserByProjectId() , action : fetching user based on projectId , data : "+  projectId + "}");
		return userRepository.findByProjectId(projectId);
	}
}
