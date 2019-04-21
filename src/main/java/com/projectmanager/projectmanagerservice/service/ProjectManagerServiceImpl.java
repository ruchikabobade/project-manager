package com.projectmanager.projectmanagerservice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projectmanager.projectmanagerservice.dao.ProjectDao;
import com.projectmanager.projectmanagerservice.dao.TaskDao;
import com.projectmanager.projectmanagerservice.dao.UserDao;
import com.projectmanager.projectmanagerservice.entity.Project;
import com.projectmanager.projectmanagerservice.entity.Task;
import com.projectmanager.projectmanagerservice.entity.User;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {

	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public User addUser(User user) {
		return userDao.addUser(user);	
	}

	@Override
	public User updateUser(User user) {
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
	public Project addProject(Project project) {
		return projectDao.addProject(project);
	}

	@Override
	public Project updateProject(Project project) {
		return projectDao.updateProject(project);
	}

	@Override
	public String suspendProject(Long projectId) {
		return projectDao.suspendProject(projectId);
	}

	@Override
	public List<Project> viewProject() {
		return projectDao.viewProject();
	}

	@Override
	public Task addTask(Task task) {
        return taskDao.addTask(task);
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

}
