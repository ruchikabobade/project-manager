package com.projectmanager.projectmanagerservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@Component
public class TaskDao {
	
	@Autowired
	private TaskRepository taskRepository;
	
	public Task addTask(Task task) {
		return taskRepository.save(task);
	}
	
	public Task updateTask(Task task) {
		return taskRepository.save(task);
	}
	
	public String endTask(Long taskId) {
		taskRepository.deleteById(taskId);
		return "Success";
	}
	
	public List<Task> viewTask(){
		return taskRepository.findAll();
	}


}
