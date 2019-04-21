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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.service.ProjectManagerService;

@RestController
@CrossOrigin(origins = {"*"})
public class ProjectManagerController {

	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private ProjectManagerService service;
	
	@PostMapping(path="/projectmanager/service/user/addUser")
	public User addUser(@RequestBody User user ) {
		logger.info("add user");
		return service.addUser(user);
	}
	
	@PutMapping(path="/projectmanager/service/user/updateUser")
	public User updateUser(@RequestBody User user) {
		return service.updateUser(user);
	}
	
	@DeleteMapping(path="/projectmanager/service/user/deleteUser/{userId}")
	public String deleteUser(@PathVariable Long userId) {
		return service.deleteUser(userId);
	}
	
	@GetMapping(path="/projectmanager/service/user/viewUser")
	public List<User> viewUser(){
		return service.viewUser();
	}
	
	@PostMapping(path="/projectmanager/service/project/addProject")
	public Project addProject(@RequestBody Project project ) {
		logger.info("add user");
		return service.addProject(project);
	}
	
	@PutMapping(path="/projectmanager/service/project/updateProject")
	public Project updateProject(@RequestBody Project project) {
		return service.updateProject(project);
	}
	
	@DeleteMapping(path="/projectmanager/service/project/suspendProject/{projectId}")
	public String suspendProject(@PathVariable Long projectId) {
		return service.suspendProject(projectId);
	}
	
	@GetMapping(path="/projectmanager/service/project/viewProject")
	public List<Project> viewProject(){
		return service.viewProject();
	}
	
	@PostMapping(path="/projectmanager/service/task/addtask")
	public Task addTask(@RequestBody Task task ) {
		logger.info("add user");
		return service.addTask(task);
	}
	
	@PutMapping(path="/projectmanager/service/task/updateTask")
	public Task updateTask(@RequestBody Task task) {
		return service.updateTask(task);
	}
	
	@DeleteMapping(path="/projectmanager/service/task/endTask/{taskId}")
	public String endTask(@PathVariable Long taskId) {
		return service.endTask(taskId);
	}
	
	@GetMapping(path="/projectmanager/service/task/viewTask")
	public List<Task> viewTask(){
		return service.viewTask();
	}
	
}
