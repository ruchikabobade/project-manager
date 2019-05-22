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
import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.ParentTaskRepository;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskDaoTests extends ProjectManagerTest{

	@InjectMocks
	private TaskDao taskDao;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Mock
	private ParentTaskRepository parentTaskRepository;
	
	@Test
	public void test_addTask() throws ProjectManagerTaskException {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(taskResponse);
		ProjectManagerRecord input = getRecord_taskInput();
		Task output = taskDao.addTask(input);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	
	@Test
	public void test_addParentTask() throws ProjectManagerTaskException {
		ParentTask taskResponse = getParentTask();
		Mockito.when(parentTaskRepository.save(Mockito.any(ParentTask.class))).thenReturn(taskResponse);
		ProjectManagerRecord input = getRecord_taskInput();
		ParentTask output = taskDao.addParentTask(input);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getParentTask(), output.getParentTask());	
	}
	

	@Test
	public void test_updateTask() throws ProjectManagerTaskException {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.save(taskResponse)).thenReturn(taskResponse);
		Task output = taskDao.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_endTask() throws ProjectManagerTaskException {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.findByTaskId(Mockito.anyLong())).thenReturn(taskResponse);
	    Mockito.when(taskRepository.save(taskResponse)).thenReturn(taskResponse);
		Task output = taskDao.endTask(taskId);
		Assert.assertNotNull(output);
		Assert.assertEquals("completed", output.getStatus());
	}
	

	@Test
	public void test_viewParentTask() throws ProjectManagerTaskException {
		List<ParentTask> tasks = getListOfParentTasks();
		Mockito.when(parentTaskRepository.findAll()).thenReturn(tasks);
		List<ParentTask> output = taskDao.viewParentTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
	@Test
	public void test_viewTaskByParent() throws ProjectManagerTaskException {
		List<ParentTask> tasks = getListOfParentTasks();
		Mockito.when(parentTaskRepository.findAllByParentTask(Mockito.anyString())).thenReturn(tasks);
		List<ParentTask> output = taskDao.viewTaskByParent("xyz");
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
	
	@Test
	public void test_viewTask() throws ProjectManagerTaskException {
		List<Task> tasks = getListOfTasks();
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		List<Task> output = taskDao.viewTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
	@Test
	public void test_viewTaskByProject() throws ProjectManagerTaskException {
		List<Task> tasks = getListOfTasks();
		Mockito.when(taskRepository.findAllByProjectId(Mockito.anyLong())).thenReturn(tasks);
		List<Task> output = taskDao.viewTaskByProject(1l);
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
	@Test
	public void test_getCompletedTasks() throws ProjectManagerTaskException {
		List<Task> tasks = getListOfTasks();
		Mockito.when(taskRepository.findAllByProjectIdAndStatus(Mockito.anyLong(), Mockito.anyString())).thenReturn(tasks);
		List<Task> output = taskDao.getCompletedTasks(1l);
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
	@Test
	public void test_getParentTaskById() throws ProjectManagerTaskException {
		ParentTask task = getParentTask();
		Mockito.when(parentTaskRepository.getOne(Mockito.anyLong())).thenReturn(task);
		ParentTask output = taskDao.getParentTaskById(1l);
		Assert.assertNotNull(output);
		Assert.assertEquals(task.getParentTask(), output.parentTask);	
	}
	
}
