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
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@RunWith(MockitoJUnitRunner.Silent.class)
public class TaskDaoTests extends ProjectManagerTest{

	@InjectMocks
	private TaskDao taskDao;
	
	@Mock
	private TaskRepository taskRepository;
	
	@Test
	public void test_addTask() {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.save(Mockito.any(Task.class))).thenReturn(taskResponse);
		ProjectManagerRecord input = getRecord_taskInput();
		Task output = taskDao.addTask(input);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_updateTask() {
		Task taskResponse = getTaskResponse();
		Mockito.when(taskRepository.save(taskResponse)).thenReturn(taskResponse);
		Task output = taskDao.updateTask(taskResponse);
		Assert.assertNotNull(output);
		Assert.assertEquals(taskResponse.getTask(), output.getTask());	
	}
	

	@Test
	public void test_endTask() {
		Mockito.doNothing().when(taskRepository).deleteById(taskId);
		String output = taskDao.endTask(taskId);
		Assert.assertNotNull(output);
		Assert.assertEquals(successMsg, output);	
	}
	

	@Test
	public void test_viewTask() {
		List<Task> tasks = getListOfTasks();
		Mockito.when(taskRepository.findAll()).thenReturn(tasks);
		List<Task> output = taskDao.viewTask();
		Assert.assertNotNull(output);
		Assert.assertEquals(tasks.size(), output.size());	
	}
	
}
