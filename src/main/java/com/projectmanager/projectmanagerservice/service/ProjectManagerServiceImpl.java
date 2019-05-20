package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.projectmanagerservice.dao.ProjectDao;
import com.projectmanager.projectmanagerservice.dao.TaskDao;
import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.ParentTask;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ParentTaskRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public User addUser(UserRecord user) {
		return userDao.addUser(user);	
	}

	@Override
	public User updateUser(ProjectManagerRecord user) {
		return userDao.updateUser(user);
	}

	@Override
	public String deleteUser(Long userId) {
		return userDao.deleteUser(userId);
		
	}

	@Override
	public List<User> viewUser() {
		return userDao.viewUser();
	}
	
	@Override
	public List<User> viewUserByFirstName(String firstName) {
		// TODO Auto-generated method stub
		 	return userDao.viewUserByFirstName(firstName);
	}


	@Override
	public ProjectManagerRecord addProject(ProjectManagerRecord projectManagerRecord) {
		Project project = projectDao.addProject(projectManagerRecord.project);
		projectManagerRecord.project.projectId = project.getProjectId();
		userDao.updateUser(projectManagerRecord);
		return projectManagerRecord;
	}

	@Override
	public Project updateProject(Project project) {
		return projectDao.updateProject(project);
	}

	@Override
	public Project suspendProject(Long projectId) {
		return projectDao.suspendProject(projectId);
	}

	@Override
	public List<Project> viewProject() {
		return projectDao.viewProject();
	}
	
	@Override
	public List<Project> viewProjectByProject(String project) {
		 	return projectDao.viewProjectByProject(project);
	}

	@Override
	public ProjectManagerRecord addTask(ProjectManagerRecord projectManagerRecord) {
		if(projectManagerRecord.isParent) {
			ParentTask parentTask = taskDao.addParentTask(projectManagerRecord);
			System.out.println(parentTask.toString());
			System.out.println(parentTask.parentId);
			ParentTaskRecord r = new ParentTaskRecord(parentTask.parentId, parentTask.parentTask);
			projectManagerRecord.parentTask = r ;
		}
		else {
		Task task = taskDao.addTask(projectManagerRecord);
		projectManagerRecord.taskId = task.getTaskId();
		}
		userDao.updateUser(projectManagerRecord);
        return projectManagerRecord;
	}

	@Override
	public Task updateTask(Task task) {
		return taskDao.updateTask(task);
	}

	@Override
	public String endTask(Long taskId) {
		return taskDao.endTask(taskId);
	}

	@Override
	public List<Task> viewTask() {
		return taskDao.viewTask();
	}
	
	@Override
	public List<ParentTask> viewTaskByParent(String parentTask) {
		return taskDao.viewTaskByParent(parentTask);
	}
	
}
