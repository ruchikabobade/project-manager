package com.projectmanager.projectmanagerservice;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;

public class ProjectManagerTest {

	public Long userId = 1L;
	public Long projectId = 1L;
	public Long taskId = 1L;
	public String successMsg = "Success";
	
	public User getUserResponse() {
		User user = new User("xyz","pqr","123",1L,1L);	
		return user;
	}
	
	public List<User> getListOfUsers(){
		List<User> users = new ArrayList();
		User user1 = new User("xyz","pqr","123",1L,1L);	
		User user2 = new User("xyz","pqr","123",1L,1L);	
		users.add(user1);
		users.add(user2);
		return users;
	}
	
	public Project getProjectResponse() {
		Project project = new Project("xyz",new Date(),new Date(),10);	
		return project;
	}
	
	public List<Project> getListOfProjects(){
		List<Project> projects = new ArrayList();
		Project project1 = new Project("xyz",new Date(),new Date(),10);
		Project project2 = new Project("pqre",new Date(),new Date(),10);
		projects.add(project1);
		projects.add(project2);
		return projects;
	}
	
	public Task getTaskResponse() {
		Task task = new Task(1l, 1l, "xyz", new Date(), new Date(), 10, "completed");	
		return task;
	}
	
	public List<Task> getListOfTasks(){
		List<Task> tasks = new ArrayList();
		Task task1 = new Task(1l, 1l, "xyz", new Date(), new Date(), 10, "completed");	
		Task task2 = new Task(1l, 1l, "xyz", new Date(), new Date(), 10, "completed");	
		tasks.add(task1);
		tasks.add(task2);
		return tasks;
	}
	

}
