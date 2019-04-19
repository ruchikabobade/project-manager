package com.projectmanager.projectmanagerservice.controller;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projectmanager.projectmanagerservice.UserTest;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.service.ProjectManagerService;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectManagerControllerTests extends UserTest {
	
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
}
