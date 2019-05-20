package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

public interface ProjectManagerService {

	public User addUser(UserRecord user);
	
	public User updateUser(ProjectManagerRecord user);
	
	public String deleteUser(Long userId);
	
	public List<User> viewUser();
	
	public List<User> viewUserByFirstName(String firstName);
	
	public ProjectManagerRecord addProject(ProjectManagerRecord project);
	
	public Project updateProject(Project project);
	
	public Project suspendProject(Long projectId);
	
	public List<Project> viewProject();
	
	public List<Project> viewProjectByProject(String project);

	public ProjectManagerRecord addTask(ProjectManagerRecord task);
	
	public Task updateTask(Task task);
	
	public String endTask(Long taskId);
	
	public List<Task> viewTask();
	
	public List<ParentTask> viewTaskByParent(String parentTask);
}
