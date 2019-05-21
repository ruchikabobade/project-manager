package com.projectmanager.projectmanagerservice.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
import com.projectmanager.projectmanagerservice.model.ProjectRecord;
import com.projectmanager.projectmanagerservice.model.ParentTaskRecord;
import com.projectmanager.projectmanagerservice.model.ProjectManagerRecord;
import com.projectmanager.projectmanagerservice.model.UserRecord;

@Service
public class ProjectManagerServiceImpl implements ProjectManagerService {
	private Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ProjectDao projectDao;
	
	@Autowired
	private TaskDao taskDao;
	
	@Override
	public User addUser(UserRecord user) throws ProjectManagerUserException {
		logger.info("In ProjectManagerServiceImpl, add user-----" + user.toString());
			return userDao.addUser(user);	
	}

	@Override
	public User updateUser(ProjectManagerRecord user) throws ProjectManagerUserException {
		logger.info("In ProjectManagerServiceImpl, update user-----" + user.toString());
		return userDao.updateUser(user);
	}

	@Override
	public String deleteUser(Long userId) throws ProjectManagerUserException{
		logger.info("In ProjectManagerServiceImpl, delete user for userid-----" + userId);
		return userDao.deleteUser(userId);
		
	}

	@Override
	public List<User> viewUser() throws ProjectManagerUserException {
		logger.info("In ProjectManagerServiceImpl, get the list of the users");
		return userDao.viewUser();
	}
	
	@Override
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException {
		logger.info("In ProjectManagerServiceImpl, get the list of the users with firstname -- " + firstName);
		 	return userDao.viewUserByFirstName(firstName);
	}


	@Override
	public ProjectManagerRecord addProject(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException {
		logger.info("In ProjectManagerServiceImpl, add project ---" + projectManagerRecord.toString());
		try {
		Project project = projectDao.addProject(projectManagerRecord.project);
		projectManagerRecord.project.projectId = project.getProjectId();
		userDao.updateUser(projectManagerRecord);
		return projectManagerRecord;
		} catch(Exception ex) {
			logger.info("Exception occured while adding the project" + ex.getMessage());
			throw new ProjectManagerUserException(500, "Error in adding project");
		}
	}

	@Override
	public Project updateProject(Project project) {
		logger.info("In ProjectManagerServiceImpl, update project ---" + project.toString());
		return projectDao.updateProject(project);
	}

	@Override
	public Project suspendProject(Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("In ProjectManagerServiceImpl, suspend project for projectId ---" + projectId);
		try {
		Project project= projectDao.suspendProject(projectId);
		List<Task> tasks = taskDao.viewTaskByProject(project.getProjectId());
		tasks.forEach((task) -> {
			task.setStatus("completed");
			try {
			taskDao.updateTask(task);
			} catch(Exception e) {
				logger.info("Exception occured while updating status of task" + e.getMessage());
			}
		});
		return project;
		}catch(Exception ex) {
			logger.info("Exception occured while suspending the project" + ex.getMessage());
			throw new ProjectManagerProjectException(500, "Error in suspending project");
		}
				
	}

	@Override
	public List<ProjectRecord> viewProject() throws ProjectManagerProjectException, ProjectManagerTaskException {
		logger.info("In ProjectManagerServiceImpl, get list of projects");
		try {
		List<Project> projectList = projectDao.viewProject();
		List<ProjectRecord> projectRecordList = new ArrayList();
		projectList.forEach((project) ->{
			ProjectRecord projectRecord = new ProjectRecord();
			projectRecord.projectId = project.getProjectId();
			projectRecord.project = project.getProject();
			projectRecord.endDate = project.getEndDate();
			projectRecord.startDate = project.getStartDate();
			projectRecord.status = project.getStatus();
			projectRecord.priority = project.getPriority();
			User user;
			try {
				user = userDao.getUserByProjectId(project.getProjectId());
				projectRecord.manager = user.getFirstName();
			} catch (ProjectManagerUserException e1) {
				logger.info("Exception occured while fetching the user by projectId" + e1.getMessage());
			}
			
			try {
				projectRecord.tasks =taskDao.viewTaskByProject(project.getProjectId()).size();
				projectRecord.completedTasks = taskDao.getCompletedTasks(project.getProjectId()).size();
			} catch (ProjectManagerTaskException e) {
				logger.info("Exception occured while fetching the task number" + e.getMessage());
			}
			projectRecordList.add(projectRecord);		
		}) ;	
		return projectRecordList;
		} catch(Exception ex) {
			logger.info("Exception occured while fetching the project list" + ex.getMessage());
			throw new ProjectManagerProjectException(500, "Error in fetching project list");
		}
	}
	
	@Override
	public List<Project> viewProjectByProjectName(String project) {
		logger.info("In ProjectManagerServiceImpl, get list of projects with name --"+ project);
		 	return projectDao.viewProjectByProjectName(project);
	}

	@Override
	public ProjectManagerRecord addTask(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException, ProjectManagerTaskException {
		logger.info("In ProjectManagerServiceImpl, add task -----" + projectManagerRecord.toString());
		try {
		if(projectManagerRecord.isParent) {
			ParentTask parentTask = taskDao.addParentTask(projectManagerRecord);
			ParentTaskRecord r = new ParentTaskRecord(parentTask.parentId, parentTask.parentTask);
			projectManagerRecord.parentTask = r ;
		}
		else {
		Task task = taskDao.addTask(projectManagerRecord);
		projectManagerRecord.taskId = task.getTaskId();
		}
		userDao.updateUser(projectManagerRecord);
        return projectManagerRecord;
		} catch(Exception ex) {
			logger.info("Exception occured while adding the task" + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in adding task");
		}
	}

	@Override
	public Task updateTask(Task task) throws ProjectManagerTaskException {	
		logger.info("In ProjectManagerServiceImpl, update task -----" + task.toString());
		return taskDao.updateTask(task);
	}

	@Override
	public Task endTask(Long taskId)  throws ProjectManagerTaskException  {
		logger.info("In ProjectManagerServiceImpl, end task for taskId -----" + taskId);
		return taskDao.endTask(taskId);
	}

	@Override
	public List<ProjectManagerRecord> viewTask()  throws ProjectManagerTaskException {
		logger.info("In ProjectManagerServiceImpl, get task list");
		try {
		List<Task> tasks = taskDao.viewTask();;
		List<ParentTask> parentTasks= taskDao.viewParentTask();
		List<ProjectManagerRecord> records =new ArrayList();
		 
		tasks.forEach((task) -> {
			ProjectManagerRecord record = new ProjectManagerRecord();
			record.taskId = task.getTaskId();
			record.project = getProjectRecord(projectDao.getProject(task.getProjectId()));
			record.endDate = task.getEndDate();
			record.startDate = task.getStartDate();
			record.priority = task.getPriority();
			record.isParent = false;
			record.task = task.getTask();
			record.status = task.getStatus();
			record.parentTask = getParentRecord(taskDao.getParentTaskById(task.getParentId()));
			records.add(record);
		});
		
		parentTasks.forEach((pt) ->{
			ProjectManagerRecord record = new ProjectManagerRecord();
			record.taskId = pt.getParentId();
			record.task = pt.getParentTask();
			record.isParent = true;
			records.add(record);
		});
		return records;
		} catch(Exception ex) {
			logger.info("Exception occured fetching tasks" + ex.getMessage());
			throw new ProjectManagerTaskException(500, "Error in fetching tasks");
		}
	}
	
	@Override
	public List<ParentTask> viewTaskByParent(String parentTask) throws ProjectManagerTaskException  {
		logger.info("In ProjectManagerServiceImpl, get parent task list");
		return taskDao.viewTaskByParent(parentTask);
	}
	
	public ProjectRecord getProjectRecord(Project project) {
		ProjectRecord pr = new ProjectRecord();
		pr.projectId = project.getProjectId();
		pr.project = project.getProject();
		return pr;
	}
 
	public ParentTaskRecord getParentRecord(ParentTask parentTask) {
		ParentTaskRecord pt = new ParentTaskRecord();
		pt.parentId = parentTask.getParentId();
		pt.parentTask = parentTask.getParentTask();
		return pt;
	}
	
}
