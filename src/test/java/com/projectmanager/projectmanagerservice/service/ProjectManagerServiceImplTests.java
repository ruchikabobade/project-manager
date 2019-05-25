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
import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException;
import com.projectmanager.projectmanagerservice.model.ParentTaskRecord;
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
	public void test_updateProject() throws ProjectManagerTaskException, ProjectManagerUserException {
		List<Task> tasks = getListOfTasks();
		ProjectManagerRecord projectResponse = getRecord_projectInput();
		Mockito.when(projectDao.addProject(projectResponse.project)).thenReturn(getProjectResponse());
		Mockito.when(userDao.updateUser(projectResponse)).thenReturn(getUserResponse());
	    Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenReturn(tasks);
		Mockito.when(taskDao.getCompletedTasks(Mockito.anyLong())).thenReturn(tasks);
		ProjectManagerRecord output = service.updateProject(projectResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(projectResponse.project, output.project);	
	}
	

	@Test
	public void test_suspendProject() throws ProjectManagerProjectException, ProjectManagerTaskException, ProjectManagerUserException {
		Project project = getProjectResponse();
		List<Task> tasks = getListOfTasks();
		project.setStatus(true);
		Task taskResponse = getTaskResponse();
		Mockito.when(projectDao.suspendProject(Mockito.anyLong())).thenReturn(project);
		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenReturn(tasks);
		Mockito.when(taskDao.updateTask(Mockito.any(Task.class))).thenReturn(taskResponse);
		Project output = service.suspendProject(projectId);
		Assert.assertNotNull(output);
		Assert.assertEquals(true, output.getStatus());	
	}
	
	@Test
	public void test_viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException, ProjectManagerUserException {
		List<Project> projects = getListOfProjects();
		User user = getUserResponse();
		List<Task> tasks = getListOfTasks();
		Mockito.when(projectDao.viewProject()).thenReturn(projects);
		Mockito.when(userDao.getUserByProjectId(Mockito.anyLong())).thenReturn(user);
		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenReturn(tasks);
		Mockito.when(taskDao.getCompletedTasks(Mockito.anyLong())).thenReturn(tasks);
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
	public void test_addTask_isParent() throws ProjectManagerUserException, ProjectManagerTaskException {
		ProjectManagerRecord taskResponse = getRecord_taskInput();
		taskResponse.isParent = true;
		Mockito.when(taskDao.addParentTask(Mockito.any(ProjectManagerRecord.class))).thenReturn(getParentTask());
		ProjectManagerRecord output = service.addTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.taskId, output.taskId);	
	}
	

	@Test
	public void test_updateTask() throws ProjectManagerTaskException {
		ProjectManagerRecord taskResponse = getRecord_taskInput();
		Task task = getTaskResponse();
		Mockito.when(taskDao.getTaskById(Mockito.anyLong())).thenReturn(task);
		ProjectManagerRecord output = service.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.task, output.task);	
	}
	
	@Test
	public void test_updateTask_isParent() throws ProjectManagerTaskException {
		ProjectManagerRecord taskResponse = getRecord_taskInput();
		Task task = getTaskResponse();
		taskResponse.isParent = true;
		//Mockito.when(taskDao.getTaskById(Mockito.anyLong())).thenReturn(task);
		Mockito.when(taskDao.addParentTask(Mockito.any(ProjectManagerRecord.class))).thenReturn(getParentTask());
		ProjectManagerRecord output = service.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.task, output.task);	
	}

	@Test
	public void test_endTask() throws ProjectManagerTaskException {
		Mockito.when(taskDao.endTask(taskId)).thenReturn(getTaskResponse());
		Task output = service.endTask(taskId);
		Assert.assertNotNull(output);
		Assert.assertEquals(true, output.getStatus());	
	}
	
	@Test
	public void test_viewProjectByProjectName() throws ProjectManagerProjectException, ProjectManagerTaskException {
		List<Project> projects = getListOfProjects();
		Mockito.when(projectDao.viewProjectByProjectName(Mockito.anyString())).thenReturn(projects);
		List<Project> output =service.viewProjectByProjectName("xyz");
		Assert.assertNotNull(output);
		Assert.assertEquals(projects.size(), output.size());	
	}

	@Test
	public void test_viewTask() throws ProjectManagerTaskException {
	List<Task> tasks = getListOfTasks();
	List<ParentTask> parentTasks = getListOfParentTasks();
	Project project = getProjectResponse();
	ParentTask parentTask = getParentTask();
		Mockito.when(taskDao.viewTask()).thenReturn(tasks);
		Mockito.when(taskDao.viewParentTask()).thenReturn(parentTasks);
		Mockito.when(projectDao.getProject(Mockito.anyLong())).thenReturn(project);
		Mockito.when(taskDao.getParentTaskById(Mockito.anyLong())).thenReturn(parentTask);
		 List<ProjectManagerRecord> output = service.viewTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(4, output.size());	
	}
	
	@Test
	public void test_viewTaskByParent() throws ProjectManagerTaskException {
		List<ParentTask> parentTasks = getListOfParentTasks();
		Mockito.when(taskDao.viewTaskByParent(Mockito.anyString())).thenReturn(parentTasks);
		 List<ParentTask> output = service.viewTaskByParent("xyz");
			Assert.assertNotNull(output);
			Assert.assertEquals(parentTasks.size(), output.size());
	}
	
	@Test
	public void test_getTaskByTaskId() throws ProjectManagerTaskException {
		Mockito.when(taskDao.getTaskById(Mockito.anyLong())).thenReturn(getTaskResponse());
		 ProjectManagerRecord output = service.getTaskByTaskId(1l);
			Assert.assertNotNull(output);
			Assert.assertEquals(getTaskResponse().getTask(), output.task );
	}
	
	@Test
	public void test_getTaskByTaskId_exception() throws ProjectManagerTaskException {
		Mockito.when(taskDao.getParentTaskById(Mockito.anyLong())).thenReturn(getParentTask());
		 ProjectManagerRecord output = service.getTaskByTaskId(1l);
			Assert.assertNotNull(output);
			Assert.assertEquals(getParentTask().parentTask, output.task );
	}
	
	@Test
	public void test_viewTaskByProjectId() throws ProjectManagerTaskException {
		List<Task> tasks = getListOfTasks();
		Project project = getProjectResponse();
		ParentTask parentTask = getParentTask();
		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenReturn(tasks);
		Mockito.when(projectDao.getProject(Mockito.anyLong())).thenReturn(project);
		Mockito.when(taskDao.getParentTaskById(Mockito.anyLong())).thenReturn(parentTask);
		 List<ProjectManagerRecord> output = service.viewTaskByProjectId(1l);
		Assert.assertNotNull(output);
		Assert.assertEquals(2, output.size());	
	}
	
	@Test
	public void test_viewParentTask() throws ProjectManagerTaskException {
		List<ParentTask> parentTasks = getListOfParentTasks();
		Mockito.when(taskDao.viewParentTask()).thenReturn(parentTasks);
		 List<ParentTask> output = service.viewParentTask();
			Assert.assertNotNull(output);
			Assert.assertEquals(parentTasks.size(), output.size());
	}
	
	@Test
	public void test_getProjectRecord() {
		Project project = getProjectResponse();
		ProjectRecord output = service.getProjectRecord(project);
		Assert.assertNotNull(output);
		Assert.assertEquals(project.getProject(), output.project);
	}
	
	@Test
	public void test_getParentRecord() {
		ParentTask pt = getParentTask();
		ParentTaskRecord output = service.getParentRecord(pt);
		Assert.assertNotNull(output);
		Assert.assertEquals(pt.getParentTask(), output.parentTask);
	}
	
	@Test
	public void test_suspendProject_error() throws Exception{
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException";
		try {
			service.suspendProject(projectId);
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_addProject_error() throws Exception{
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerUserException";
		try {
			service.addProject(getRecord_project());
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_addTask_error() throws Exception{
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException";
		try {
			service.addTask(getRecord_taskInput());
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_viewTaskByProjectId_error() throws Exception{
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException";
		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenThrow(new RuntimeException());
		try {
			service.addTask(getRecord_taskInput());
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_updateTask_exception() throws ProjectManagerUserException, ProjectManagerTaskException {
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException";
		ProjectManagerRecord taskResponse = getRecord_taskInput();
		Mockito.when(taskDao.addParentTask(Mockito.any(ProjectManagerRecord.class))).thenReturn(getParentTask());
		try {
			service.updateTask(taskResponse);
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_viewTask_exception() throws ProjectManagerTaskException {
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException";
		Mockito.when(taskDao.viewTask()).thenThrow(new RuntimeException());
		try {
			service.viewTask();
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_viewTaskByProjectId_exception() throws ProjectManagerTaskException {
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException";
		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenThrow(new RuntimeException());
		try {
			service.viewTaskByProjectId(projectId);
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
	@Test
	public void test_viewProject_exception() throws ProjectManagerProjectException, ProjectManagerTaskException, ProjectManagerUserException {
		String exception = "com.projectmanager.projectmanagerservice.exception.ProjectManagerProjectException";
		List<Project> projects = getListOfProjects();
		User user = getUserResponse();
		List<Task> tasks = getListOfTasks();
		Mockito.when(projectDao.viewProject()).thenThrow(new RuntimeException());
//		Mockito.when(userDao.getUserByProjectId(Mockito.anyLong())).thenReturn(user);
//		Mockito.when(taskDao.viewTaskByProject(Mockito.anyLong())).thenReturn(tasks);
//		Mockito.when(taskDao.getCompletedTasks(Mockito.anyLong())).thenReturn(tasks);
//		List<ProjectRecord> output =service.viewProject();
//		Assert.assertNotNull(output);
//		Assert.assertEquals(projects.size(), output.size());	
//		
		try {
			service.viewProject();
		}catch(Exception ex) {
			 Assert.assertEquals(exception, ex.toString());	
		}
	}
	
}

