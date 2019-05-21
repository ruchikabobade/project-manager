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
			return userDao.addUser(user);	
	}

	@Override
	public User updateUser(ProjectManagerRecord user) throws ProjectManagerUserException {
		return userDao.updateUser(user);
	}

	@Override
	public String deleteUser(Long userId) throws ProjectManagerUserException{
		return userDao.deleteUser(userId);
		
	}

	@Override
	public List<User> viewUser() throws ProjectManagerUserException {
		return userDao.viewUser();
	}
	
	@Override
	public List<User> viewUserByFirstName(String firstName) throws ProjectManagerUserException {
		 	return userDao.viewUserByFirstName(firstName);
	}


	@Override
	public ProjectManagerRecord addProject(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException {
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
	public Project suspendProject(Long projectId) throws ProjectManagerProjectException, ProjectManagerTaskException {
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
	public List<Project> viewProjectByProject(String project) {
		 	return projectDao.viewProjectByProject(project);
	}

	@Override
	public ProjectManagerRecord addTask(ProjectManagerRecord projectManagerRecord) throws ProjectManagerUserException, ProjectManagerTaskException {
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
	}

	@Override
	public Task updateTask(Task task) throws ProjectManagerTaskException {	
		return taskDao.updateTask(task);
	}

	@Override
	public Task endTask(Long taskId)  throws ProjectManagerTaskException  {
		return taskDao.endTask(taskId);
	}

	@Override
	public List<ProjectManagerRecord> viewTask()  throws ProjectManagerTaskException {
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
			record.parentTask = getParentRecord(taskDao.getParentTaskById(task.getTaskId()));
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
	}
	
	@Override
	public List<ParentTask> viewTaskByParent(String parentTask) throws ProjectManagerTaskException  {
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
