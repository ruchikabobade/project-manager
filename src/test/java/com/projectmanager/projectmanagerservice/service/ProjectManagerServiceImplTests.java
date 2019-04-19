package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projectmanager.projectmanagerservice.UserTest;
import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.User;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectManagerServiceImplTests extends UserTest {
	
	@InjectMocks
	private ProjectManagerServiceImpl service;
	
	@Mock
	private UserDao userDao;

	@Test
	public void test_addUser() {
		User userResponse = getUserResponse();
		Mockito.when(userDao.addUser(userResponse)).thenReturn(userResponse);
		User output = service.addUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_updateUser() {
		User userResponse = getUserResponse();
		Mockito.when(userDao.updateUser(userResponse)).thenReturn(userResponse);
		User output = service.updateUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_deleteUser() {
		Mockito.when(userDao.deleteUser(userId)).thenReturn(successMsg);
		String output = service.deleteUser(userId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewUser() {
		List<User> users = getListOfUsers();
		Mockito.when(userDao.viewUser()).thenReturn(users);
		List<User> output = service.viewUser();
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
}
