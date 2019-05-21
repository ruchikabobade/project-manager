package com.projectmanager.projectmanagerservice;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ParentTaskRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

public class ProjectManagerTest {

	public Long userId = 1L;
	public Long projectId = 1L;
	public Long taskId = 1L;
	public String successMsg = "Success";
	
	public UserRecord getUserRecordResponse() {
		UserRecord user = new UserRecord(null, "XYZ", "POLO", "12345");	
		return user;
	}
	
	public User getUserResponse() {
		User user = new User("XYZ","pqr","123",1L,1L);	
		return user;
	}
	
	public ProjectManagerRecord getRecord_user() {
		
		ProjectRecord project = new ProjectRecord(1L, "XYZ", false, null, null, 0,0,0);
		UserRecord user = new UserRecord(0L, "XYZ", "POLO", "12345");	
		ProjectManagerRecord record = new ProjectManagerRecord(0L, "", null, null, 0, "", false, null, user, project);	
		return record;
	}
	
	public ProjectManagerRecord getRecord_projectInput() {
		ProjectRecord project = new ProjectRecord(0L, "XYZ", false, null, null, 0,0,0);
		UserRecord user = new UserRecord(1L, "XYZ", "POLO", "12345");	
		ProjectManagerRecord record = new ProjectManagerRecord(0L, "", null, null, 0, "", false, null, user, project);	
		return record;	
	}
	
	public ProjectManagerRecord getRecord_project() {
		ProjectRecord project = new ProjectRecord(1L, "XYZ", false, null, null, 10,0,0);
		UserRecord user = new UserRecord(0L, "XYZ", "POLO", "12345");	
		ProjectManagerRecord record = new ProjectManagerRecord(0L, "", null, null, 0, "", false, null, user, project);	
		return record;
	}
	
	public ProjectManagerRecord getRecord_taskInput() {
		ProjectRecord project = new ProjectRecord(1L, "XYZ", false, null, null, 0,0,0);
		UserRecord user = new UserRecord(1L, "XYZ", "POLO", "12345");	
		ParentTaskRecord parent = new ParentTaskRecord(1L,"polo");
		ProjectManagerRecord record = new ProjectManagerRecord(0L, "xyz", new Date(), new Date(), 10, "completed", false, parent, user, project);	
		return record;
	}
	
	public ProjectManagerRecord getRecord_task() {
		ProjectRecord project = new ProjectRecord(1L, "XYZ", false, null, null, 0,0,0);
		UserRecord user = new UserRecord(0L, "XYZ", "POLO", "12345");	
		ParentTaskRecord parent = new ParentTaskRecord(0L,"polo");
		ProjectManagerRecord record = new ProjectManagerRecord(1L, "xyz", new Date(), new Date(), 10, "completed", false, parent, user, project);	
		return record;
	}
	
	public List<ProjectManagerRecord> getRecords_task() {
		List<ProjectManagerRecord> records = new ArrayList();
		ProjectRecord project = new ProjectRecord(1L, "XYZ", false, null, null, 0,0,0);
		UserRecord user = new UserRecord(0L, "XYZ", "POLO", "12345");	
		ParentTaskRecord parent = new ParentTaskRecord(0L,"polo");
		ProjectManagerRecord record = new ProjectManagerRecord(1L, "xyz", new Date(), new Date(), 10, "completed", false, parent, user, project);	
		records.add(record);
		return records;
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
		Project project = new Project("xyz",new Date(),new Date(),10, false);	
		return project;
	}
	
	public List<Project> getListOfProjects(){
		List<Project> projects = new ArrayList();
		Project project1 = new Project("xyz",new Date(),new Date(),10, false);
		Project project2 = new Project("pqre",new Date(),new Date(),10, false);
		projects.add(project1);
		projects.add(project2);
		return projects;
	}
	
	public List<ProjectRecord> getListOfProjectRecords(){
		List<ProjectRecord> projects = new ArrayList();
		ProjectRecord project1 = new ProjectRecord(1L, "XYZ", false, null, null, 10,0,0);
		ProjectRecord project2 = new ProjectRecord(2L, "PQR", false, null, null, 10,0,0);
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
