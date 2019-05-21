package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import com.projectmanager.projectmanagerservice.ProjectManagerTest;
import com.projectmanager.projectmanagerservice.dao.ProjectDao;
import com.projectmanager.projectmanagerservice.dao.TaskDao;
import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

@RunWith(MockitoJUnitRunner.Silent.class)
public class ProjectManagerServiceImplTests extends ProjectManagerTest {
	
	@InjectMocks
	private ProjectManagerServiceImpl service;
	
	@Mock
	private UserDao userDao;
	
	@Mock
	private ProjectDao projectDao;
	
	@Mock
	private TaskDao taskDao;

	@Test
	public void test_addUser() throws ProjectManagerUserException {
		UserRecord userResponse = getUserRecordResponse();
		Mockito.when(userDao.addUser(userResponse)).thenReturn(getUserResponse());
		User output = service.addUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.firstName, output.getFirstName());	
	}
	

	@Test
	public void test_updateUser() throws ProjectManagerUserException {
		ProjectManagerRecord userResponse = getRecord_user();
		Mockito.when(userDao.updateUser(userResponse)).thenReturn(getUserResponse());
		User output = service.updateUser(userResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(userResponse.user.firstName, output.getFirstName());	
	}
	

	@Test
	public void test_deleteUser() throws ProjectManagerUserException {
		Mockito.when(userDao.deleteUser(userId)).thenReturn(successMsg);
		String output = service.deleteUser(userId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewUser() throws ProjectManagerUserException {
		List<User> users = getListOfUsers();
		Mockito.when(userDao.viewUser()).thenReturn(users);
		List<User> output = service.viewUser();
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
	
	@Test
	public void test_viewUserByFirstName() throws ProjectManagerUserException {
		List<User> users = getListOfUsers();
		Mockito.when(userDao.viewUserByFirstName("xyz")).thenReturn(users);
		List<User> output = service.viewUserByFirstName("xyz");
		Assert.assertNotNull(output);
		Assert.assertEquals(users.size(), output.size());	
	}
	
	@Test
	public void test_addProject() throws ProjectManagerUserException {
		ProjectManagerRecord projectResponse = getRecord_projectInput();
		Mockito.when(projectDao.addProject(projectResponse.project)).thenReturn(getProjectResponse());
		Mockito.when(userDao.updateUser(projectResponse)).thenReturn(getUserResponse());
		ProjectManagerRecord output = service.addProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.project.projectId, output.project.projectId);	
	}
	

	@Test
	public void test_updateProject() {
		Project projectResponse = getProjectResponse();
		Mockito.when(projectDao.updateProject(projectResponse)).thenReturn(projectResponse);
		Project output = service.updateProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.getProject(), output.getProject());	
	}
	

	@Test
	public void test_suspendProject() throws ProjectManagerProjectException, ProjectManagerTaskException, ProjectManagerUserException {
		Project project = getProjectResponse();
		project.setStatus(true);
		Mockito.when(projectDao.suspendProject(projectId)).thenReturn(project);
		Project output = service.suspendProject(projectId);
		Assert.assertNotNull(output);
		Assert.assertEquals(true, output.getStatus());	
	}
	

	@Test
	public void test_viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException {
		List<Project> projects = getListOfProjects();
		Mockito.when(projectDao.viewProject()).thenReturn(projects);
		List<ProjectRecord> output =service.viewProject();
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}
	
	@Test
	public void test_addTask() throws ProjectManagerUserException, ProjectManagerTaskException {
		ProjectManagerRecord taskResponse = getRecord_taskInput();
		Mockito.when(taskDao.addTask(taskResponse)).thenReturn(getTaskResponse());
		ProjectManagerRecord output = service.addTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.taskId, output.taskId);	
	}
	

	@Test
	public void test_updateTask() throws ProjectManagerTaskException {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskDao.updateTask(taskResponse)).thenReturn(taskResponse);
		Task output = service.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_endTask() throws ProjectManagerTaskException {
		Mockito.when(taskDao.endTask(taskId)).thenReturn(getTaskResponse());
		Task output = service.endTask(taskId);
		Assert.assertNotNull(output);
		Assert.assertEquals("completed", output.getStatus());	
	}
	

//	@Test
//	public void test_viewTask() throws ProjectManagerTaskException {
//		List<Task> tasks = getListOfTasks();
//		Mockito.when(taskDao.viewTask()).thenReturn(tasks);
//		 List<ProjectManagerRecord> output = service.viewTask();
//		Assert.assertNotNull(output);
//		Assert.assertEquals(tasks.size(), output.size());	
//	}
}

