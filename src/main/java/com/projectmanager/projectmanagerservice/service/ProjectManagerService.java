package com.projectmanager.projectmanagerservice.service;

import java.util.List;

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

public interface ProjectManagerService {

	public User addUser(UserRecord user) throws ProjectManagerUserException;
	
	public User updateUser(ProjectManagerRecord user) throws ProjectManagerUserException;
	
	public String deleteUser(Long userId) throws ProjectManagerUserException;
	
	public List<User> viewUser() throws ProjectManagerUserException;
	
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException;
	
	public ProjectManagerRecord addProject(ProjectManagerRecord project) throws ProjectManagerUserException;
	
	public Project updateProject(Project project);
	
	public Project suspendProject(Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException;
	
	public List<ProjectRecord> viewProject() throws ProjectManagerProjectException,  ProjectManagerTaskException;
	
	public List<Project> viewProjectByProjectName(String project);

	public ProjectManagerRecord addTask(ProjectManagerRecord task) throws ProjectManagerUserException, ProjectManagerTaskException ;
	
	public Task updateTask(Task task)  throws ProjectManagerTaskException ;
	
	public Task endTask(Long taskId)  throws ProjectManagerTaskException ;
	
	public List<ProjectManagerRecord> viewTask()  throws ProjectManagerTaskException ;
	
	public List<ParentTask> viewTaskByParent(String parentTask)  throws ProjectManagerTaskException ;
	
	public ProjectManagerRecord getTaskByTaskId(Long taskId);
	
	public List<ProjectManagerRecord> viewTaskByProjectId(Long ProjectId)  throws ProjectManagerTaskException ;
	
}
