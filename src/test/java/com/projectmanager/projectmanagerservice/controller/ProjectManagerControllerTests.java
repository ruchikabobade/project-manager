package com.projectmanager.projectmanagerservice.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projectmanager.projectmanagerservice.ProjectManagerTest;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.service.ProjectManagerService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectManagerControllerTests extends ProjectManagerTest {
	
	@InjectMocks
	private ProjectManagerController projectManagerController;
	
	@Mock
	private ProjectManagerService service;

	@Test
	public void test_addUser() {
		User userResponse = getUserResponse();
		Mockito.when(service.addUser(userResponse)).thenReturn(userResponse);
		User output = projectManagerController.addUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_updateUser() {
		User userResponse = getUserResponse();
		Mockito.when(service.updateUser(userResponse)).thenReturn(userResponse);
		User output = projectManagerController.updateUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_deleteUser() {
		Mockito.when(service.deleteUser(userId)).thenReturn(successMsg);
		String output = projectManagerController.deleteUser(userId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewUser() {
		List<User> users = getListOfUsers();
		Mockito.when(service.viewUser()).thenReturn(users);
		List<User> output = projectManagerController.viewUser();
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
	
	@Test
	public void test_addProject() {
		Project projectResponse = getProjectResponse();
		Mockito.when(service.addProject(projectResponse)).thenReturn(projectResponse);
		Project output = projectManagerController.addProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}
	

	@Test
	public void test_updateProject() {
		Project projectResponse = getProjectResponse();
		Mockito.when(service.updateProject(projectResponse)).thenReturn(projectResponse);
		Project output = projectManagerController.updateProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}
	

	@Test
	public void test_suspendProject() {
		Mockito.when(service.suspendProject(projectId)).thenReturn(successMsg);
		String output = projectManagerController.suspendProject(projectId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewProject() {
		List<Project> projects = getListOfProjects();
		Mockito.when(service.viewProject()).thenReturn(projects);
		List<Project> output = projectManagerController.viewProject();
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}
	
	@Test
	public void test_addTask() {
		Task taskResponse = getTaskResponse();
		Mockito.when(service.addTask(taskResponse)).thenReturn(taskResponse);
		Task output = projectManagerController.addTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_updateTask() {
		Task taskResponse = getTaskResponse();
		Mockito.when(service.updateTask(taskResponse)).thenReturn(taskResponse);
		Task output = projectManagerController.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_endTask() {
		Mockito.when(service.endTask(taskId)).thenReturn(successMsg);
		String output = projectManagerController.endTask(taskId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewTask() {
		List<Task> tasks = getListOfTasks();
		Mockito.when(service.viewTask()).thenReturn(tasks);
		List<Task> output = projectManagerController.viewTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
}
