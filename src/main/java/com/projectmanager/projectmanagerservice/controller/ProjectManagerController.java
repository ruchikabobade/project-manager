package com.projectmanager.projectmanagerservice.controller;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;
import com.projectmanager.projectmanagerservice.service.ProjectManagerService;

@RestController
@CrossOrigin(origins = {"*"})
public class ProjectManagerController {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private ProjectManagerService service;
	
	@PostMapping(path="/projectmanager/service/user/addUser")
	public User addUser(@RequestBody UserRecord user ) throws ProjectManagerUserException {
		logger.info("Add user ---" + user.toString());
		return service.addUser(user);
	}
	
	@PutMapping(path="/projectmanager/service/user/updateUser")
	public User updateUser(@RequestBody ProjectManagerRecord user)  throws ProjectManagerUserException{
		logger.info("Update user ----" + user.toString());
		return service.updateUser(user);
	}
	
	@DeleteMapping(path="/projectmanager/service/user/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) throws ProjectManagerUserException {
		logger.info("Delete user for user Id ---- " + userId);
		return service.deleteUser(userId);
	}
	
	@GetMapping(path="/projectmanager/service/user/viewUser")
	public List<User> viewUser() throws ProjectManagerUserException{
		logger.info("Fetch the list of users");
		return service.viewUser();
	}
	

	@GetMapping(path="/projectmanager/service/user/viewUserByFirstName/{firstName}")
	public List<User> viewUserByFirstName(@PathVariable String firstName) throws ProjectManagerUserException{
		logger.info("Fetch the list of users filtered by first name ------ " + firstName);
		return service.viewUserByFirstName(firstName);
	}
	
	@PostMapping(path="/projectmanager/service/project/addProject")
	public ProjectManagerRecord addProject(@RequestBody ProjectManagerRecord project ) throws ProjectManagerUserException{
		logger.info("Add project ----- " + project.toString());
		return service.addProject(project);
	}
	
	@PutMapping(path="/projectmanager/service/project/updateProject")
	public Project updateProject(@RequestBody Project project) {
		logger.info("Update project -------" + project.toString());
		return service.updateProject(project);
	}
	
	@DeleteMapping(path="/projectmanager/service/project/suspendProject/{projectId}")
	public Project suspendProject(@PathVariable Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("Suspend project for project Id --------" + projectId);
		return service.suspendProject(projectId);
	}
	
	@GetMapping(path="/projectmanager/service/project/viewProject")
	public List<ProjectRecord> viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException{
		logger.info("Fetch the list of projects");
		return service.viewProject();
	}
	
	@GetMapping(path="/projectmanager/service/project/viewProjectByProject/{project}")
	public List<Project> viewProjectByFirstName(@PathVariable String project){
		logger.info("Fetch the list of projects filtered by project name ------" + project);
		return service.viewProjectByProject(project);
	}
	
	@PostMapping(path="/projectmanager/service/task/addtask")
	public ProjectManagerRecord addTask(@RequestBody ProjectManagerRecord task ) throws ProjectManagerUserException, ProjectManagerTaskException {
		logger.info("Add task -----" + task.toString());
		return service.addTask(task);
	}
	
	@PutMapping(path="/projectmanager/service/task/updateTask")
	public Task updateTask(@RequestBody Task task) throws ProjectManagerTaskException {
		logger.info("Update task -------" + task.toString());
		return service.updateTask(task);
	}
	
	@DeleteMapping(path="/projectmanager/service/task/endTask/{taskId}")
	public Task endTask(@PathVariable Long taskId) throws ProjectManagerTaskException {
		logger.info("End task for task Id ---- " + taskId);
		return service.endTask(taskId);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTask")
	public List<ProjectManagerRecord> viewTask() throws ProjectManagerTaskException{
		logger.info("Fetch the list of tasks");
		return service.viewTask();
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTaskByParent/{parentTask}")
	public List<ParentTask> viewTaskByParent(@PathVariable String parentTask) throws ProjectManagerTaskException{
		logger.info("Fetch the list of parent tasks with task ------" + parentTask);
		return service.viewTaskByParent(parentTask);
	}
	
}
