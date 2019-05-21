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
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskDaoTests extends ProjectManagerTest{

	@InjectMocks
	private TaskDao taskDao;
	
	@Mock
	private TaskRepository taskRepository;
	
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
	public void test_updateTask() throws ProjectManagerTaskException {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.save(taskResponse)).thenReturn(taskResponse);
		Task output = taskDao.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

//	@Test
//	public void test_endTask() throws ProjectManagerTaskException {
//		Mockito.when(taskRepository.findByTaskId(Mockito.any())).thenReturn(getTaskResponse());
//		Task output = taskDao.endTask(taskId);
//		Assert.assertNotNull(output);	
//	}
	

	@Test
	public void test_viewTask() throws ProjectManagerTaskException {
		List<Task> tasks = getListOfTasks();
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		List<Task> output = taskDao.viewTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
}
