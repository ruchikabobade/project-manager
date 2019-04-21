package com.projectmanager.projectmanagerservice.dao;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projectmanager.projectmanagerservice.ProjectManagerTest;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserDaoTests extends ProjectManagerTest {

	@InjectMocks
	private UserDao userDao;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void test_addUser() {
		User userResponse = getUserResponse();
		Mockito.when(userRepository.save(userResponse)).thenReturn(userResponse);
		User output = userDao.addUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_updateUser() {
		User userResponse = getUserResponse();
		Mockito.when(userRepository.save(userResponse)).thenReturn(userResponse);
		User output = userDao.updateUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_deleteUser() {
		Mockito.doNothing().when(userRepository).deleteById(userId);
		String output = userDao.deleteUser(userId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewUser() {
		List<User> users = getListOfUsers();
		Mockito.when( userRepository.findAll()).thenReturn(users);
		List<User> output = userDao.viewUser();
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
}
