package com.projectmanager.projectmanagerservice.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.ParentTaskRepository;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@Component
public class TaskDao {
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	public Task addTask(ProjectManagerRecord projectManagerRecord) {
		Task task = new Task();
		task.setTask(projectManagerRecord.task);
		task.setParentId(projectManagerRecord.parentTask.parentId);
		task.setEndDate(projectManagerRecord.endDate);
		task.setStartDate(projectManagerRecord.startDate);
		task.setProjectId(projectManagerRecord.project.projectId);
		task.setPriority(projectManagerRecord.priority);
		task.setStatus(projectManagerRecord.status);
		return taskRepository.save(task);
		
	}
	
	public ParentTask addParentTask(ProjectManagerRecord projectManagerRecord) {
			ParentTask parentTask = new ParentTask();
			parentTask.setParentTask(projectManagerRecord.task);
		return parentTaskRepository.save(parentTask);
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

	public List<ParentTask> viewTaskByParent(String parentTask){
		return parentTaskRepository.findAllByParentTask(parentTask);
	}


}
