package com.projectmanager.projectmanagerservice.dao;

import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.exception.ProjectManagerTaskException;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.repository.ParentTaskRepository;
import com.projectmanager.projectmanagerservice.repository.TaskRepository;

@Component
public class TaskDao {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	
	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private ParentTaskRepository parentTaskRepository;
	
	public Task addTask(ProjectManagerRecord projectManagerRecord) throws ProjectManagerTaskException {
		try {
		logger.info("In TaskDao, Adding task ----------" + projectManagerRecord.toString());
		Task task = new Task();
		task.setTask(projectManagerRecord.task);
		task.setTaskId(projectManagerRecord.taskId);
		task.setParentId(projectManagerRecord.parentTask.parentId);
		task.setEndDate(projectManagerRecord.endDate);
		task.setStartDate(projectManagerRecord.startDate);
		task.setProjectId(projectManagerRecord.project.projectId);
		task.setPriority(projectManagerRecord.priority);
		task.setStatus(projectManagerRecord.status);
		return taskRepository.save(task);
		} catch(Exception ex){
			logger.info("Exception occured while adding task -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in adding task");
		}
		
	}
	
	public ParentTask addParentTask(ProjectManagerRecord projectManagerRecord) throws ProjectManagerTaskException {
		try {
			logger.info("In TaskDao, Adding Parent task ----------" + projectManagerRecord.toString());
			ParentTask parentTask = new ParentTask();
			parentTask.setParentId(projectManagerRecord.taskId);
			parentTask.setParentTask(projectManagerRecord.task);
		return parentTaskRepository.save(parentTask);
		} catch(Exception ex){
			logger.info("Exception occured while adding parent task -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in adding parent task");
		}
		
	}
	
	public Task updateTask(Task task) throws ProjectManagerTaskException {
		try {
			logger.info("In TaskDao, Updating task ----------" + task.toString());
		return taskRepository.save(task);
		} catch(Exception ex){
			logger.info("Exception occured while updating task -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in adding updating task ");
		}
	}
	
	public Task endTask(Long taskId) throws ProjectManagerTaskException {
		try {
			logger.info("In TaskDao, Updating task status for taskId --------" + taskId);
			Task task = taskRepository.findByTaskId(taskId);
			task.setStatus("completed");	
		return taskRepository.save(task);
		}
		catch(Exception ex){
			logger.info("In TaskDao, Exception occured while updating task status for taskId -- " +taskId +" , error---" + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in adding updating task status");
		}
	}
	
	public List<Task> viewTask() throws ProjectManagerTaskException{
		try {
			logger.info("In TaskDao, Fetching list of tasks");
		return taskRepository.findAll();
		}
		catch(Exception ex){
			logger.info("Exception occured while fetching task list  -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching task list");
		}
	}

	public List<ParentTask> viewParentTask() throws ProjectManagerTaskException{
		try {
			logger.info("In TaskDao, Fetching list of parent tasks");
		return parentTaskRepository.findAll();
		}
		catch(Exception ex){
			logger.info("Exception occured while fetching parent task list  -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching parent task list");
		}
	}
	public List<ParentTask> viewTaskByParent(String parentTask) throws ProjectManagerTaskException{
		try {
			logger.info("In TaskDao, Fetching list of parent tasks for task name ---" + parentTask);
		return parentTaskRepository.findAllByParentTask(parentTask);
		}
		catch(Exception ex){
			logger.info("Exception occured while fetching parent task list  -- " + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching parent task list");
		}
	}
	
	public List<Task> viewTaskByProject(Long projectId) throws ProjectManagerTaskException{
		try {
			logger.info("In TaskDao, Fetching list of tasks for projectId ---" + projectId);
		return taskRepository.findAllByProjectId(projectId);
		}
		catch(Exception ex){
			logger.info("Exception occured while fetching task list for projectId -- " + projectId  +" , error ---" + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching task list");
		}
	}
	
	public List<Task> getCompletedTasks(Long projectId) throws ProjectManagerTaskException{
		try {
			logger.info("In TaskDao, Fetching list of completed tasks for projectId ---" + projectId);
			String status = "completed";
		return taskRepository.findAllByProjectIdAndStatus(projectId, status);
		}
		catch(Exception ex){
			logger.info("Exception occured while fetching completed task list for projectId -- " + projectId  +" , error ---" + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching completed task list");
		}
	}
	
	public ParentTask getParentTaskById(Long id) {
		logger.info("In TaskDao, getting parent task by id -- " +id);
		return parentTaskRepository.getOne(id);
	}
	
	public Task getTaskById(Long id) {
		logger.info("In TaskDao, getting task by id ----" + id);
		return taskRepository.findByTaskId(id);
	}
}
