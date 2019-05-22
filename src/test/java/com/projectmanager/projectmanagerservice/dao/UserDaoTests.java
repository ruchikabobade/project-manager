package com.projectmanager.projectmanagerservice.dao;

import java.sql.SQLException;
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
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.repository.UserRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class UserDaoTests extends ProjectManagerTest {

	@InjectMocks
	private UserDao userDao;
	
	@Mock
	private UserRepository userRepository;
	
	@Test
	public void test_addUser_successResponse() throws ProjectManagerUserException {
		User userResponse = getUserResponse();
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userResponse);
		User output = userDao.addUser(getUserRecordResponse());
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_updateUser_successResponse() throws ProjectManagerUserException {
		User userResponse = getUserResponse();
		Mockito.when(userRepository.save(Mockito.any(User.class))).thenReturn(userResponse);
		User output = userDao.updateUser(getRecord_user());
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.getFirstName(), output.getFirstName());	
	}
	

	@Test
	public void test_deleteUser_successResponse() throws ProjectManagerUserException {
		Mockito.doNothing().when(userRepository).deleteById(userId);
		String output = userDao.deleteUser(userId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewUser_successResponse() throws ProjectManagerUserException {
		List<User> users = getListOfUsers();
		Mockito.when( userRepository.findAll()).thenReturn(users);
		List<User> output = userDao.viewUser();
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
	
	@Test
	public void test_viewUserByFirstName_successResponse() throws ProjectManagerUserException {
		List<User> users = getListOfUsers();
		Mockito.when( userRepository.findAllByFirstName(Mockito.anyString())).thenReturn(users);
		List<User> output = userDao.viewUserByFirstName("xyz");
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
	
	@Test
	public void test_getUserByProjectId() throws ProjectManagerUserException {
		User user = getUserResponse();
		Mockito.when( userRepository.findByProjectId(Mockito.anyLong())).thenReturn(user);
		User output = userDao.getUserByProjectId(1L);
		Assert.assertNotNull(output);
		Assert.assertEquals(user.getFirstName(), output.getFirstName());	
	}
	
	@Test
	public void test_addUser_errorResponse() throws ProjectManagerUserException {
		User userResponse = getUserResponse();
		Mockito.doReturn(new Exception()).when(userRepository).save(userResponse);
		try {
		userDao.addUser(getUserRecordResponse());
		} catch(Exception ex) {
			 Assert.assertEquals("Error in adding user", ex.getMessage());	
	}
	}
	

	@Test
	public void test_updateUser_errorResponse() throws ProjectManagerUserException {
		User userResponse = getUserResponse();
		Mockito.doReturn(new Exception()).when(userRepository).save(userResponse);
		try {
			userDao.updateUser(getRecord_user());
		} catch(Exception ex) {
			 Assert.assertEquals("Error in updating user", ex.getMessage());	
	}	
	}
	

}
