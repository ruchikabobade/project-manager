package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;

public interface ProjectManagerService {

	public User addUser(User user);
	
	public User updateUser(User user);
	
	public String deleteUser(Long userId);
	
	public List<User> viewUser();
	
	public Project addProject(Project project);
	
	public Project updateProject(Project project);
	
	public String suspendProject(Long projectId);
	
	public List<Project> viewProject();

	public Task addTask(Task task);
	
	public Task updateTask(Task task);
	
	public String endTask(Long taskId);
	
	public List<Task> viewTask();
}
